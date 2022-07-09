package simplefileparser.item;

public abstract class Item<T> {

    T item;

    public Item(T item) {
        this.item = item;
    }

    public abstract String getName();
    public abstract String getType();

    public T get() {
        return item;
    }


}
