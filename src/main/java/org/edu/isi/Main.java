package org.edu.isi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.edu.isi.entities.Product;
import org.edu.isi.services.JpaUtils;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
   static int menu(){
        int option = 0;
        Scanner sc = new Scanner(System.in);
        while(option< 1 || option>4){
            System.out.println("**********************************\n");
            System.out.println("1.Create A New Product \n");
            System.out.println("2.Create A New Basket \n");
            System.out.println("3.Create A New Product_Basket \n");
            System.out.println("4.Exit \n");
            option = sc.nextInt();
            System.out.println("**********************************");
            if(option <1 || option>4){
                System.out.println("Please enter a number between 1 and 4");
            }
        }
        return option;
    }

    static boolean createProduct(){
        EntityManager em = JpaUtils.getEmf();
        boolean result = false;
        Scanner sc = new Scanner(System.in);
        String product_name ;
        String product_code;
        double product_price;

        System.out.println("Please enter the product name: ");
        product_name = sc.nextLine();
        System.out.println("Please enter the product code: ");
        product_code = sc.nextLine();
        System.out.println("Please enter the product price: ");
        product_price = sc.nextDouble();

        Product product = new Product(product_code,product_price,product_name);
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            result = true;
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

        return result;
    }
    static void message(boolean bool, String typeOfObject){
       if(bool){
           System.out.println("new "+typeOfObject+" added successfully");
       }else{
           System.out.println(typeOfObject+" not added ");
       }
    }

    public static void main(String[] args) {
        int option = menu();
        System.out.println(option);
        EntityManager em = JpaUtils.getEmf();
        EntityTransaction et = em.getTransaction();
        boolean result ;
        try {
            switch (option){
                case 1:
                    result = createProduct();
                    message(result, "Product");
                    break;
                default :System.out.println("Please enter a number between 1 and 4");
            }
            et.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}