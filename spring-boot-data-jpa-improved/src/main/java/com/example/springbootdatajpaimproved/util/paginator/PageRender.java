package com.example.springbootdatajpaimproved.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPage;
    private int numElementosPorPagina;

    private List<PageItem> lPages;
    private int actualPage;

    public PageRender(String url,Page<T> page){
        int desde,hasta;
        this.url = url;
        this.page = page;
        this.totalPage = page.getTotalPages();
        this.numElementosPorPagina = page.getSize();
        this.actualPage = page.getNumber() + 1 ; //Pagina actual: el offset es 0, pere se ha demostrar desde 1 hasta X.
        this.lPages = new ArrayList<PageItem>();

        //Realizmaos el cálculo:
        if(totalPage<=numElementosPorPagina){
            //Si por ejemplo tenemos 100 registros y son 10 paginas, y el número de elementos por pagian que queremos
            // mostrar son 10, va ha mostrar las 10 paginas en el paginadao de una sola vez
            desde = 1;
            hasta = totalPage;
        }else{
            // Por el contrario, si tenemos 5 paginas y el número de elemnetos es 20 (5*20=100 elementos)
            // y se quieren mostrar 10 elementos por págiga, se va a navegar por rango
            // comprobamos el rano
            if(actualPage<=numElementosPorPagina/2){
                //First range: Muestra desde el elemenot 1 hasta el numero de elementos por pagina
                //Muestra del elmento 1 hasta el 10
                desde = 1;
                hasta = numElementosPorPagina;
            }else if(actualPage>= totalPage - numElementosPorPagina/2){
                //End range: Muestra desde
                desde = totalPage - numElementosPorPagina + 1;
                hasta = totalPage;
            }else{
                //Intermedy range
                desde = actualPage - numElementosPorPagina/2;
                hasta = numElementosPorPagina;
            }
        }

        //Rellenamos las paginas con cada uno de los times
        for(int i=0;i<hasta;i++){
            lPages.add(new PageItem(desde+i,actualPage == desde+i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<PageItem> getlPages() {
        return lPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    //GET addicioanles
    public boolean isFirstPage(){
        return page.isFirst();
    }

    public boolean isLastPage(){
        return page.isLast();
    }

    public boolean isHasNextPage(){
        return page.hasNext();
    }

    public boolean isHasPreviousPage(){
        return page.hasPrevious();
    }
}
