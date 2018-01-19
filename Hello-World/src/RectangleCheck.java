import java.util.Scanner;

public class RectangleCheck {

	//Write a program that prompts the user to enter the center coordinates (x and y), width, and height 
	//of two rectangles and determines whether the second rectangle is inside the first or overlaps with the first.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean allowThrough = true;
		String[] coordinate1 = {};
		String[] others1 = {};
		String[] coordinate2 = {};
		String[] others2 = {};
		do {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.print("Enter Center Coordinates For Rectangle 1 'x,y': ");
				coordinate1 = scan.nextLine().split(",");
				System.out.print("Enter Width and Height for Center Coordinate 1 as 'width,height': ");
				others1 = scan.nextLine().split(",");
				
				System.out.print("Enter Center Coordinates For Rectangle 2 'x,y': ");
				coordinate2 = scan.nextLine().split(",");
				System.out.print("Enter Width and Height for Center Coordinate 2 as 'width,height': ");
				others2 = scan.nextLine().split(",");
				scan.close();
				allowThrough=true;
			}catch(Exception e) {System.out.println("Information was not input correctly, please try again.");allowThrough=false;}
		}while(!allowThrough);
		
		if(Double.parseDouble(coordinate1[0]) < Double.parseDouble(coordinate2[0]))	//if the 2nd triangle has a larger x value
		{
			if (Double.parseDouble(coordinate1[1]) < Double.parseDouble(coordinate2[1]))//2nd triangle has larger y value
			{
				if( Double.parseDouble(coordinate2[0]) - Double.parseDouble(coordinate1[0]) - Double.parseDouble(others1[0]) - Double.parseDouble(others2[0]) <= 0 && Double.parseDouble(coordinate2[1]) - Double.parseDouble(coordinate1[1]) - Double.parseDouble(others1[1]) - Double.parseDouble(others2[1]) <= 0 )
					System.out.println("The second rectangle is either inside or overlaps with the first triangle");
				else
					System.out.println("The second rectangle does NOT either inside or overlaps with the first triangle");
			}else {
				if(Double.parseDouble(coordinate2[0]) - Double.parseDouble(coordinate1[0]) - Double.parseDouble(others1[0]) - Double.parseDouble(others2[0]) <= 0 && Double.parseDouble(coordinate1[1]) - Double.parseDouble(coordinate2[1]) - Double.parseDouble(others1[1]) - Double.parseDouble(others2[1]) <= 0 )
					System.out.println("The second rectangle is either inside or overlaps with the first triangle");
				else
					System.out.println("The second rectangle does NOT either inside or overlaps with the first triangle");
			}
		}else {	//2nd triangle has smaller x value
			if (Double.parseDouble(coordinate1[1]) < Double.parseDouble(coordinate2[1]))//2nd triangle has larger y value
			{
				if( Double.parseDouble(coordinate1[0]) - Double.parseDouble(coordinate2[0]) - Double.parseDouble(others1[0]) - Double.parseDouble(others2[0]) <= 0 && Double.parseDouble(coordinate2[1]) - Double.parseDouble(coordinate1[1]) - Double.parseDouble(others1[1]) - Double.parseDouble(others2[1]) <= 0 )
					System.out.println("The second rectangle is either inside or overlaps with the first triangle");
				else
					System.out.println("The second rectangle does NOT either inside or overlaps with the first triangle");
			}else {//2nd triangle has smaller y value
				if(Double.parseDouble(coordinate1[1]) == Double.parseDouble(coordinate2[1]) && Double.parseDouble(coordinate1[0]) == Double.parseDouble(coordinate2[0]))//2nd triangle DOES NOT HAVE same coordinates as 1st triangle
				{
					if( Double.parseDouble(coordinate1[0]) - Double.parseDouble(coordinate2[0]) - Double.parseDouble(others1[0]) - Double.parseDouble(others2[0]) <= 0 && Double.parseDouble(coordinate1[1]) - Double.parseDouble(coordinate2[1]) - Double.parseDouble(others1[1]) - Double.parseDouble(others2[1]) <= 0 )
						System.out.println("The second rectangle is either inside or overlaps with the first triangle");
					else
						System.out.println("The second rectangle does NOT either inside or overlaps with the first triangle");
				}else {//2nd triangle DOES have same coordinates as 1st triangle
					System.out.println("The second rectangle is either inside or overlaps with the first triangle");
				}
			}
		}
	}

}
