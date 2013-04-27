 public class SomeClass {
                    public static void main(String[] args) {
                        SomeClass c = new SomeClass();
                        
                        c.f1(1,1);                        
                    }
                    public void f1(Integer i1, Integer i2) {
                        System.out.println("1");
                    }
                    public void f1(Integer i1, int i2) {
                        System.out.println("2");
                    }
                    public void f1(int b, Integer c) {
                        System.out.println("1");
                    }
                    public void f1(long b, long c) {
                        System.out.println("1");
                    }
                }
 
                class SomeChild1 extends SomeClass {
                    public void print() {
                        System.out.println("SomeChild1");
                    }
                }
                class SomeChild2 extends SomeClass {
                    public void print() {
                        System.out.println("SomeChild2");
                    }
                }
