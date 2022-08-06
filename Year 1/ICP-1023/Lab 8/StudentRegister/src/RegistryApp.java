
public class RegistryApp
{

	public static void main(String[] args)
	{
		//create the registry object
		Registry theRegistry = new Registry();
		
		//create an interface
		RegistryCLI theRegistryCLI
			= new RegistryCLI(theRegistry);
		
		//display the menu
		theRegistryCLI.doMenu();
	}

}
