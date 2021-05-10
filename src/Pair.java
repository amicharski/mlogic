public class Pair<T, U> {
    private T key;
    private U value;

    public Pair(T key, U value){
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public U getValue() {
        return value;
    }

    public void setValue(U value) {
        this.value = value;
    }

    public void setPair(T key, U value){
        setKey(key);
        setValue(value);
    }

    @Override
    public String toString(){
        return "{" + getKey() + ", " + getValue() + "}";
    }
}
