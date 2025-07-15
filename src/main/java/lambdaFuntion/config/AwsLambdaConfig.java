package lambdaFuntion.config;

import jakarta.servlet.Filter;
import lambdaFuntion.domain.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {
    //supplier<O> == GET
    //Function<I,O>
    //Consumer<I> == POST

    @Bean
    public Filter getFilter(){
        return new SecurityFilter();
    }

    @Bean(name = "Saludar")
    Supplier<String> greeting(){
        return ()-> "Hello World!";
    }

    @Bean
    public Consumer<String> printParam(){
        return (param) -> {
            System.out.println("Consumer.printParam : " + param);
        };
    }

    @Bean
    public Function<String, String> reverse(){
        return (s)-> String.valueOf(new StringBuilder(s).reverse());
    }

    @Bean
    public Function<String, String> recieveParam(){
        return (param)-> {
            String name = param.toUpperCase();
            return name;
        };
    }

    @Bean
    public Supplier<Map<String, Object>> createCharacter(){
        return ()->{
          Map<String, Object> character = new HashMap<>();
          character.put("name","Goku");
          character.put("healthPoint", 100);
          character.put("skill", "Kame Hame Ha!");

            return character;
        };
    }

    @Bean
    public Function<Map<String, Object>, String> receiveCharacter(){
        return (param)->{
            param.forEach((key,value)-> System.out.println(key + " - " + value.toString()));
            return "Personaje recibido";
        };
    }

    @Bean
    public Function<Character, Character> receiveAnObject(){
        return (param)->param;
    }

    @Bean
    public Function<Map<String,Object>, Map<String, Object>> processCharacters(){
        return (param)-> {
          Map<String,Object> mapCharacter = param;
          mapCharacter.forEach((key,value)-> System.out.println(key + " - "+ value.toString()));

          Map<String,Object> newCharacter = new HashMap<>();
          newCharacter.put("name","Krillin");
          newCharacter.put("healthPoints",50);
          newCharacter.put("Skills", new String[] {"Ki En Zan!","Kame Hame Ha!"} );

          return newCharacter;
        };
    }
}
