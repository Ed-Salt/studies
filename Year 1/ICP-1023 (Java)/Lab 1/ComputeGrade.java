public class GradeCalculator
{
	public static void main(String[] args) 
	{
		int cl1mark = 7;
		int cl2mark = 13;
		int cl3mark = 15;
		int cl4mark = 10;
		int clmax = 20;
		System.out.println("Class Tests (Marked out of " + clmax + ")");
		System.out.println("Class Test 1    " + cl1mark + "/" + clmax);
		System.out.println("Class Test 2    " + cl2mark + "/" + clmax);
		System.out.println("Class Test 3    " + cl3mark + "/" + clmax);
		System.out.println("Class Test 4    " + cl4mark + "/" + clmax);
		int cltotal = cl1mark + cl2mark + cl3mark + cl4mark;
		int clper = (cltotal*100)/(clmax*4);
		System.out.println("Total           " + cltotal + "/" + (clmax*4) + " = " + clper + "%\n\n");
		int la1mark = 8;
		int la2mark = 8;
		int la3mark = 7;
		int la4mark = 9;
		int lamax = 10;
		System.out.println("Laboratory Work (Marked out of " + lamax + ")");
		System.out.println("Lab 1            " + la1mark + "/" + lamax);
		System.out.println("Lab 2            " + la2mark + "/" + lamax);
		System.out.println("Lab 3            " + la3mark + "/" + lamax);
		System.out.println("Lab 4            " + la4mark + "/" + lamax);
		int latotal = la1mark + la2mark + la3mark + la4mark;
		int laper = (latotal*100)/(lamax*4);
		System.out.println("Total           " + latotal + "/" + (lamax*4) + " = " + laper + "%\n\n");
		int pass = 75;
		System.out.println("Programming Assignment");
		System.out.println("Assignment      " + pass + "%\n\n");
		System.out.println("Weighted Total = (" + clper + "% * 0.4) + (" + laper + "% * 0.3) + (" + pass + "% * 0.3)");
		double clwei = ((int) (((clper*0.4)*10)+0.5))/10.0;
		int lawei = (int) (laper*0.3);
		double pawei = pass*0.3;
		System.out.println("               = " +  clwei + " + " + lawei + " + " + pawei + " = " + (clwei+lawei+pawei));
		int towei = (int) ((clwei+lawei+pawei)+0.5);
		System.out.println("               = " + towei + "%");
	}

}