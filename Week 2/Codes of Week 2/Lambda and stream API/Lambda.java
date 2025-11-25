public class Lambda {
    public static void main(String[] args) {

        // 1st way -> by the first way
//        Walkable obj = new WalkFast();
//        obj.walk(5,true);

        // 2nd way -> not the need of class implementing interface, on the go create class and override methods
    Walkable obj2 = new Walkable(){
        @Override
        public int walk(int steps, boolean isEnabled) {
            System.out.println("your steps are : "+ steps);
            return 2*steps;
        }
    };

    // 3rd way -> now we are using Lambda function to create an object of functionalInterface only
        // and on the fly we define the method and create the object
        // and we do not need to declare the datatype of the parameter of the function as their is only one function so compiler know that what are the sequence of the parameter , the name of the function and dataType
        // arguments name should not be necessary to same but good practice is use same
        // here now no need to write the interface on the write hand side
        Walkable obj3 = (steps, isEnabled) -> {
            System.out.println("Walking fast "+ steps +" steps.");
            return 2*steps;
        };
        Walkable obj4 = (steps, isEnabled) -> 2*steps;
        //to return directly from the Lambda function do not use {} just simple write that statement it will return.
        System.out.println(obj4.walk(4, true));
       System.out.println(obj3.walk(7,true));



    }
}

//an interface having only single method is known as functionInterface and in this we can use the Lambda function to override and define the function
// their is not need to mention @FunctionalInterface it is by default consider
@FunctionalInterface
interface Walkable{
    int walk(int steps,boolean isEnabled);
}
// the 1st way we define a class which implement this interface and override the function

class WalkFast implements Walkable{
    @Override
    public int walk(int steps,boolean isEnabled){
        System.out.println("your steps are : "+ steps);
        return 2*steps;

    }
}
