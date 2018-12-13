import java.util.Arrays;
import java.util.Objects;

public class MyList<E> {
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private int size = 0;
  private Object[] elements;

  public MyList() {
    this.elements = EMPTY_ELEMENTDATA;
  }

  public MyList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elements = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.elements = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
        initialCapacity);
    }
  }

  public void add(int index, E element) {
    rangeCheckForAdd(index);
    final int s;
    Object[] elementData;
    if ((s = size) == (elementData = this.elements).length)
      elementData = grow(size + 1);
    System.arraycopy(elementData, index,
      elementData, index + 1,
      size - index);
    elementData[index] = element;
    size = size + 1;
  }

  private void rangeCheckForAdd(int index) {
    if (index > size || index < 0)
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  private String outOfBoundsMsg(int index) {
    return "Index: "+index+", Size: "+size;
  }

  private Object[] grow(int minCapacity) {
    return elements = Arrays.copyOf(elements,
      newCapacity(minCapacity));
  }

  private int newCapacity(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elements.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity <= 0) {
      if (elements == EMPTY_ELEMENTDATA)
        return Math.max(DEFAULT_CAPACITY, minCapacity);
      if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
      return minCapacity;
    }
    return (newCapacity - MAX_ARRAY_SIZE <= 0)
      ? newCapacity
      : hugeCapacity(minCapacity);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
      throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE)
      ? Integer.MAX_VALUE
      : MAX_ARRAY_SIZE;
  }

  public E remove(int index) {
    Objects.checkIndex(index, size);
    final int newSize;
    final Object[] es = elements;
    E oldValue = (E) es[index];
    if ((newSize = size - 1) > index)
      System.arraycopy(es, index + 1, es, index, newSize - 1);
    es[size = newSize] = null;
    return oldValue;
  }

  public int size() {
    return size;
  }

  public Object clone() {
    try {
      MyList<?> v = (MyList<?>) super.clone();
      v.elements = Arrays.copyOf(elements, size);
      return v;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e);
    }
  }

  public boolean contains(E o) {
    return indexOf(o , 0, size) >= 0;
  }

  public int indexOf(Object o, int start, int end) {
    Object[] es = elements;
    if (o == null) {
      for (int i = start; i < end; i++) {
        if (es[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = start; i < end; i++) {
        if (o.equals(es[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  public boolean add(E o) {
    if (size == elements.length)
      elements = grow(size+1);
    elements[size] = o;
    size = size + 1;
    return true;
  }

  /**
   * Increases the capacity of this {@code ArrayList} instance, if
   * necessary, to ensure that it can hold at least the number of elements
   * specified by the minimum capacity argument.
   *
   * @param minCapacity the desired minimum capacity
   */
  public void ensureCapacity(int minCapacity) {
    if (minCapacity > elements.length
      && !(elements == EMPTY_ELEMENTDATA
      && minCapacity <= DEFAULT_CAPACITY)) {
      grow(minCapacity);
    }
  }

  public E get(int index) {
    return (E) elements[index];
  }

  public void clear() {
    final Object[] es = elements;
    for (int to = size, i = size = 0; i < to; i++) {
      es[i] = null;
    }
  }
}
