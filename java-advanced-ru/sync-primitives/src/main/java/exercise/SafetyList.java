package exercise;

class SafetyList {
    // BEGIN
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    // END

    public synchronized void add(Object object) {
        if (size == elementData.length - 1) {
            resize(elementData.length * 2);
        }
        this.elementData[size++] = object;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    public Object get(int index) {
        return this.elementData[index];
    }

    public int getSize() {
        return this.size;
    }
}
