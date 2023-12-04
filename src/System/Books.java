package System;

public class Books {
    private int id;
    private String title;
    private int qty;
    private String author;
    private int page;


    public Books (int id, String title, String author, int page, int qty){
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
        this.qty = qty;
    }

    public Books(){

    }

    public int getId() {
        return id;
    }


    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getQty(){
        return qty;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", qty=" + qty +
                '}';
    }
}
