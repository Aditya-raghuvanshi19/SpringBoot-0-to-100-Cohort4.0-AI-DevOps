import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) {


        //once a stream is run then again we can not run the stream as stream runs and then it will be closed
        //if we again want to run get error that stream is runs and closed
        //Each time a stream is processed the previous stream is closed and a new stream will be created. To prevent memory leaking.
        List<String> fruits = List.of("Apple","Banana","Mango","kiwi","orange");
        //Stream<String> fruitsStream = fruits.stream();
//        fruitsStream.forEach((fruit)-> {
//            System.out.println(fruit);
//        } );

//        fruitsStream
//                .filter((fruit) -> fruit.length() < 6)
//                .sorted()
//                .map(f->f.length())
//                .map((fruitLength)-> 2*fruitLength)
//                .forEach((fruit -> System.out.println(fruit)));

//        List<Integer> fruitsLengthList = fruits.stream()
//                .map((f)->f.length())
//                .collect(Collectors.toList());
        //the collect method uses collectors
        //the collector is use to collect all those stream into a required collections it can be : List , Set , Map only

//        System.out.println(fruitsLengthList);

        Map<String , Integer> fruiteMap = fruits
                .stream()
                .collect(Collectors.toMap(
                        key -> key,
                        value -> value.length()
                ));

        System.out.println(fruiteMap);

        // lambda method reference is used when we do not want to define the variable name in the lambda function we use  this so it is like -->    className :: methodName   so we have to remember of which class the method is then it will directly applied on the variable

        // ex :  value -> value.length()  this lambda function can be written as  String::length

        //  during lambda function we do not define the datatype of the argument just write the variable name

    }
}
