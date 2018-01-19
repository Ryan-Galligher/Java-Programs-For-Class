import java.text.DecimalFormat;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
/*		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 3 points for a triangle: ");
		String stuff1 = scan.nextLine();
		String stuff2 = scan.nextLine();
		String stuff3 = scan.nextLine();
		scan.close();
		
		String[] thing1 = stuff1.split(" ");
		String[] thing2 = stuff2.split(" ");
		String[] thing3 = stuff3.split(" ");

		
		double x1=Double.parseDouble(thing1[0]);
		double y1=Double.parseDouble(thing1[1]);
		double x2=Double.parseDouble(thing2[0]);
		double y2=Double.parseDouble(thing2[1]);
		double x3=Double.parseDouble(thing3[0]);
		double y3=Double.parseDouble(thing3[1]);
		
		//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3);
		
		double side1 = Math.pow((Math.pow((x2-x1),2) + Math.pow((y2-y1),2)),.5);
		double side2 = Math.pow((Math.pow((x3-x2),2) + Math.pow((y3-y2),2)),.5);
		double side3 = Math.pow((Math.pow((x3-x1),2) + Math.pow((y3-y1),2)),.5);
		
		//System.out.println(side1 + " " + side2 + " " + side3);
		
		double s = (side1 + side2 + side3)/2;
		double area = Math.pow( (s*(s-side1)*(s-side2)*(s-side3)), .5 );
		 DecimalFormat df = new DecimalFormat("#.#");
		//System.out.println(s + " " + area);
		System.out.println("The area of the triangle is " + df.format(area));
		
		int status = 200;
		
		switch(status)
		{
		    case 200: System.out.println("OK");break;
		    case 403: System.out.println("forbidden");break;
		    case 404: System.out.println("not found");break;
		    case 500: System.out.println("server error");break;
		}
		
		String stuff = "janfebmaraprmayjunjulaugsepoctnovdec".substring((status-1)*3, (status-1)*3+3);
	*/
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a point with two coordinates: ");
		String stuff1 = scan.nextLine();
		scan.close();
		double reclen = 10/5;
		double recwid = 5/2;
		String[] thing1 = stuff1.split(" ");
		double x=Double.parseDouble(thing1[0]);
		double y=Double.parseDouble(thing1[1]);
		boolean isIn = (x < reclen && (x *-1) > (reclen * -1) && y < recwid && (y *-1) > (recwid * -1));
		System.out.println( "Point (" + x + "," + y + " " + ((isIn) ? "is":"is not") + " in the rectangle");
	}
}
