package com.example.springbootdatajpaimproved.util.paginator;

public class PageItem {
    private int num; //numero de pagina
    private boolean isActualPage; //flag que indica si es pagina acual o no

    public PageItem(int num, boolean isActualPage) {
        this.num = num;
        this.isActualPage = isActualPage;
    }

    public int getNum() {
        return num;
    }

    public boolean isActualPage() {
        return isActualPage;
    }
}
