import java.util.*;

public class DoubleStack {

   private LinkedList<Double> m = new LinkedList<Double>();
	
   public static void main (String[] argum) {	   
   }

   DoubleStack() {
   }
  

   @Override
   public Object clone() throws CloneNotSupportedException {
	  DoubleStack clone = new DoubleStack();
	  
	  for (int i= (m.size()-1); i >= 0; i--)
		  clone.push(m.get(i));
	 
      return clone;
   }

   public boolean stEmpty() {
	  //System.out.println(m.isEmpty()); 
      return m.isEmpty();
   }

   public void push (double a) {
	   //System.out.println("Push "+a);
	   m.addFirst(a);
   }

   public double pop() {
	  double d = m.getFirst();
	  m.removeFirst();
      return d;
   }

   public void op (String s) {
	   double b = m.pop();
	   double a = m.pop();
	   
	   if (s.equals ("+")) 
	   {
		   //System.out.println(b +" "+ s +" "+ a);
		   m.push (b + a);
	   }
	   else if (s.equals ("-")) 
	   {
		   //System.out.println(a +" "+ s +" "+ b);
		   m.push (a - b);
	   }
	   else if (s.equals ("*")) 
	   {
		   //System.out.println(b +" "+ s +" "+ a);
		   m.push (b * a);
	   }
	   else if (s.equals ("/")) 
	   {
		   //System.out.println(a +" "+ s +" "+ b);
		   m.push (a / b);
	   }
	   else
		   throw new RuntimeException();
   }
  
   public double tos() {
	  //System.out.println(m.size());
	  double d = m.getFirst();
	  //System.out.println(m.size());
      return d;
   }

   @Override
   public boolean equals (Object o) {
	   
	   DoubleStack ds = (DoubleStack) o;
	   
	   if (m.size() != ds.m.size())
		   return false;
	   
	   
	   int i = 0;
	   for (double c: m)
	   {
		   //System.out.println(c +" "+ ds.m.get(i));
		   if (c != ds.m.get(i))
			   return false;
		  
		   i++;
	   }
      return true;
   }

   @Override
   public String toString() {
	   StringBuffer b = new StringBuffer();
	   
	   for (int i= (m.size()-1); i >= 0; i--)
		  b.append(m.get(i));
	  
	  //System.out.println(b);
	   
      return b.toString();
   }

   public static double interpret (String pol) {
	   
	   StringTokenizer tokens = new StringTokenizer(pol, " \t");
		
	   // create a new obj
	   DoubleStack ds = new DoubleStack();
	   	   
	   int i = 1;
	   int tc = tokens.countTokens();
	   while (tokens.hasMoreElements()) {
			String token = (String) tokens.nextElement();
			//System.out.println(token);
			
			// If double put to stack
			if (token.equals("-") || token.equals("+") || token.equals("/") || token.equals("*"))
			{
				//System.out.println("Do operation:" + token);
				ds.op(token);
				//System.out.println("Answer:" + ds.tos());
			}
			else
			{				
				//System.out.println(tc+ " : " + i);
				if (tc == i && i > 2) // When we have more than two elements we assume operator is last one
					throw new RuntimeException();
				
				double temp = Double.parseDouble(token);
				//System.out.println("Argument:" + temp);
				ds.push(temp);
			}
			i++;
		}
		
	   return ds.tos();
   }

}