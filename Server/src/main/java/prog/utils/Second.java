package prog.utils;

public class Second {
    private String book_id,book_title;

    public Second(String book_id, String book_title) {
        this.book_id = book_id;
        this.book_title = book_title;
    }

    public Second() {

    }

    public String getId() {
        return book_id;
    }

    public void setId(String id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

}