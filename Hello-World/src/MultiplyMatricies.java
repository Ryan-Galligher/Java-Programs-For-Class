import java.util.Formatter;
import java.util.Scanner;

public class MultiplyMatricies
{
	public static void main(String [] args)
	{
		int[][] matrix1;
		int[][] matrix2;
		int[][] finalMatrix;
		Scanner sc = new Scanner(System.in);
		String[] input;
		
		System.out.print("Input dimenstions of Matrix 1 (in intxint format): ");
		input = sc.nextLine().split("x");
		matrix1 = new int[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
		System.out.print("Input dimenstions of Matrix 2 (in intxint format): ");
		input = sc.nextLine().split("x");
		matrix2 = new int[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
		
		if(legalOperation(matrix1, matrix2))
			finalMatrix = new int[matrix1.length][matrix2[0].length];
		else
			throw new IllegalArgumentException("Matricies are not multipliable");
		
		inputMatricies(sc, matrix1, 1);
		inputMatricies(sc, matrix2, 2);
		
		multiplyMatricies(matrix1, matrix2, finalMatrix);
		
		System.out.println("Final Calculated Matrix: ");
		printMatrix(finalMatrix);
	}
	
	public static void inputMatricies(Scanner sc, int[][] matrix, int matrixNum) throws IllegalArgumentException
	{
		String[] str;
		for(int i = 0; i < matrix.length; i++)
		{
			System.out.print("Enter information for Matrix " + matrixNum + " row " + i +" and seperate with spaces: ");
			str = sc.nextLine().split(" ");
			if(str.length != matrix[i].length)
				throw new IllegalArgumentException("There were too many inputed values");
			for(int a = 0; a < matrix[i].length;a++)
			{
				matrix[i][a] = Integer.parseInt(str[a]);
			}
		}
	}
	
	public static boolean legalOperation(int[][] matrix1, int[][] matrix2)
	{
		return (matrix1[0].length == matrix2.length);
	}
	
	public static void multiplyMatricies(int[][] matrix1, int[][] matrix2, int[][] finalMatrix)
	{
		int total = 0;
		for(int row1 = 0; row1 < matrix1.length; row1++)	//for every row in the first matrix
		{
			for(int col2 = 0; col2 < matrix2[0].length; col2++)	//multiply that row with every column in the second matrix
			{
				for(int col1=0; col1<matrix1[row1].length;col1++)	//for every item in the row and column currently "selected"
				{
					total += matrix1[row1][col1] * matrix2[col1][col2];
				}
				finalMatrix[row1][col2] = total;
				total=0;
			}
		}
	}
	
	public static void printMatrix(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int a = 0; a < matrix[i].length; a++)
			{
				System.out.print(matrix[i][a] + " ");
			}
			System.out.println("");
		}
	}
}
