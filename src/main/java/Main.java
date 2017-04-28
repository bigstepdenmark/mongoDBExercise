import java.util.Scanner;

/**
 * Created by ismailcam on 28/04/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        Controller ctrl = new Controller();

        Scanner scanner = new Scanner( System.in );

        System.out.println(":::::::::::::::::::::::::::::::::::::");
        System.out.println("Welcome to Twitter statistics");
        System.out.println("Commands: users, top 10 users, most mentioned users, most active users, most grumpy twetters, most happy twetters");
        System.out.println("To exit the program type exit");
        System.out.println(":::::::::::::::::::::::::::::::::::::");

        while( scanner.hasNext() )
        {
            String input = scanner.nextLine();

            if( input.equals( "users" ) )
                ctrl.countUsers();

            else if( input.equals( "top 10 users" ) )
                ctrl.topUsers( 10 );

            else if( input.equals( "most mentioned users" ) )
                System.out.println( "Is you!!!" );

            else if( input.equals( "most active users" ) )
                ctrl.mostActiveUsers( 10 );

            else if( input.equals( "most grumpy twetters" ) )
                ctrl.mostGrumpy( 10 );

            else if( input.equals( "most happy twetters" ) )
                ctrl.mostHappy( 10 );

            else if( input.equals( "exit" ) )
                System.exit( 0 );
            else{
                System.err.println("Wrong command!");
                System.out.println("Commands: users, top 10 users, most mentioned users, most active users, most grumpy twetters, most happy twetters");
            }

        }
    }
}
