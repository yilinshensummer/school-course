package csv;

import java.util.Iterator;
import java.util.ListIterator;


/**
 * A generic LinkedList class with nested classes Node and ListIterator. The LinkedList class maintains a list of Nodes,
 * allowing the user to add a node, insert at a particular index or get the size of the list, or create a new iterator.
 * The Nodes class holds data of a generic type and a reference to the next node in the list. The ListIterator class
 * iterates through each Node of the LinkedList.
 *
 * @author Foothill College,Yilin Shen
 *
 * @param <T>        A generic.
 */
public class LinkedList <T> implements Iterable<T>
{
   
   private Node head;
   private Node tail;
   private int size;
   
   /**
    * A Node that holds generic data and a reference to the next Node of a LinkedList.
    *
    * @author Foothill College, Yilin Shen
    */
   public class Node {
      
      private T data;
      private Node next;
      
      /**
       * A constructor that sets the data of the Node and the next attribute to null.
       *
       * @param newData        The data of the Node.
       */
      public Node(T newData)
      {
         this.data = newData;
         this.next = null;
      }
      
      /**
       * A constructor that sets the data and the next reference to the arguments.
       *
       * @param newData        The data of the Node.
       * @param n              The reference to the Next Node.
       */
      public Node(T newData, Node n)
      {
         this.data = newData;
         this.next = n;
      }
      
      /**
       * A getter method that returns the data of the Node.
       *
       * @return        Returns the data of the Node.
       */
      public T getData(){
         return data;
      }
      
      /**
       * A getter method that returns the next Node in the LinkedList.
       *
       * @return        Returns the next Node in the LinkedList.
       */
      public Node getNext(){
         return next;
      }
      
      /**
       * A setter method that sets the next reference of the Node.
       *
       * @param next        The next Node of the LinkedList.
       */
      public void setNext(Node next){
         this.next = next;
      }
      
      /**
       * A toString method that returns a string representation of the data.
       *
       * @return        Returns a string representation of the data.
       */
      public String toString() {
         return data.toString();
      }
      
   }
   
   /**
    * A class that iterates through each Node of the LinkedList.
    *
    * @author Foothill College, Yilin Shen
    */
   private class ListIterator implements Iterator<T> {
      
      private Node current;
      
      /**
       * A constructor that sets the current attribute to the head of the LinkedList.
       *
       */
      public ListIterator()
      {
         current = head;
      }
      
      /**
       * A methods that checks if there is a next Node in the LinkedList.
       *
       * @return        Returns a boolean statement.
       */
      @Override
      public boolean hasNext() {
         if (current == null)
            return false;
         return true;
      }
      
      /**
       * A method that gets the next Nodes of the LinkedList for the iterator object.
       *
       * @return        The next Node of the LinkedList.
       */
      @Override
      public T next() {
         if (current == null)
         {
            throw new java.util.NoSuchElementException();
         }
         
         T data = current.data;
         current = current.next;
         return data;
      }
      
      /**
       * A method that removes a Node from the LinkedList (currently not supported).
       *
       */
      @Override
      public void remove() {
         
         throw new UnsupportedOperationException();
      }
      
   }
   
   /**
    * A default constructor for the LinkedList class.
    *
    */
   public LinkedList()
   {
      head = null;
      size = 0;
      tail = null;
   }
   
   /**
    * A method that adds a new Node to the LinkedList.
    *
    * @param node        A node for the LinkedList.
    */
   public void add(T node)
   {
      Node current = new Node(node);
      
      if (head == null)
      {
         head = current;
         tail = head;
      }
      else
      {
         tail.setNext(current);
         tail = current;
      }
      size++;
      
   }
   
   /**
    * A method that checks if the LinkedList contains a particular object.
    *
    * @param requestedObject        The searched for in the LinkedList.
    * @return                       Returns the requested object, otherwise returns null.
    */
   public T contains(T requestedObject)
   {
      
      Node current = head;
      while (current != null)
      {
         T data = current.getData();
         if (data.equals(requestedObject))
            return data;
         current = current.getNext();
      }
      return null;
   }
   
   /**
    * A method that gets the object at a particular index of the LinkedList.
    *
    * @param requestedIndex        The index that will be searched for.
    * @return                      The object at the requestedIndex, else throws and exception.
    */
   public T getIndex(int requestedIndex)
   {
      Node walker = head;
      int index = 0;
      
      if (requestedIndex < 0)
         throw new IndexOutOfBoundsException();
      if (requestedIndex >= size)
         throw new IndexOutOfBoundsException();
      
      while (walker != null && index <= requestedIndex)
      {
         if (index == requestedIndex)
            return walker.getData();
         
         walker = walker.getNext();
         index++;
      }
      return null;
   }
   
   /**
    * A method that inserts an a requested object into the requested index of the LinkedList.
    *
    * @param objectToInsert        The object to be inserted into the LinkedList.
    * @param index                 The index where the object is to be inserted.
    */
   public void insertAtIndex(T objectToInsert, int index)
   {
      
      if (index < 0)
         throw new IndexOutOfBoundsException();
      
      
      else if (index == 0)
      {
         head = new Node(objectToInsert);
         tail = head;
         size++;
         return;
      }
      
      else if (index > size)
         add(objectToInsert);
      
      else
      {
         Node walker = head;
         
         for (int findIndex = 0; findIndex < index; findIndex++)
            walker = walker.next;
         
         Node temp = walker.next;
         
         walker.next = new Node(objectToInsert);
         
         (walker.next).next = temp;
         
         size++;
      }
   }
   
   /**
    * Creates a new iterator object.
    *
    * @return        Returns a new iterator object.
    */
   @Override
   public Iterator<T> iterator()
   {
      return new ListIterator();
   }
   
   /**
    * Gets the size of the LinkedList
    *
    * @return        Returns the size of the LinkedList.
    */
   public int size()
   {
      return size;
   }
   
   
   /**
    * A toString method that returns the string representation of the LinkedList.
    *
    * @return        Returns the string representation of the LinkedList.
    */
   public String toString()
   {
      
      StringBuilder result = new StringBuilder();
      
      Node walker = head;
      int index = 0;
      
      if (getIndex(0) instanceof Country)
      {
         Country data = (Country)getIndex(0);
         int startYear = data.getStartYear();
         int endYear = data.getEndYear();
         int range = endYear - startYear + 1;
         
         result.append(String.format("%10s%52s", "Index", "Country Names"));
         
         for (int years = 0; years < range; years++)
         {
            result.append(String.format("%20s", startYear + years));
         }
      }
      result.append(String.format("\n"));
      
      // Representation of the data
      while (walker != null)
      {
         result.append(String.format("%10s%52s", index, walker.toString()));
         
         walker = walker.getNext();
         index++;
      }
      
      return result.toString();
   }
   
}
