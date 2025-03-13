package org.edu.isi;

import io.sentry.Sentry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.edu.isi.entities.Basket;
import org.edu.isi.entities.Product;
import org.edu.isi.entities.ProductBasket;
import org.edu.isi.services.JpaUtils;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
   static final Logger logger = LogManager.getLogger(Main.class);

    static int menu(){
       // logger.info("Menu Menu");
        //logger.warn("une erreur tres grave ");
        Sentry.captureMessage("Menu Menu");
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
            if(option <1 || option>5){
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
        double product_price = 0;

        System.out.println("Please enter the product name: ");
        product_name = sc.nextLine();
        System.out.println("Please enter the product code: ");
        product_code = sc.nextLine();
        try{
            System.out.println("Please enter the product price: ");
            product_price = sc.nextDouble();
        } catch (Exception e) {
            logger.error("une valeur non numerique a été saisie comme prix lors de la creation d'un produit {}",e.getMessage());
            Sentry.captureException(e);
            return false;
        }


        try {
            em.getTransaction().begin();
            Product product = new Product(product_code,product_price,product_name);
            em.persist(product);
            em.getTransaction().commit();
            result = true;
            logger.info("le produit {} a bien ete creer ",product_name);
        }catch (Exception e){
            em.getTransaction().rollback();
            logger.error("erreur lors de la creation du produit {}",e.getMessage());
            Sentry.captureException(e);
        }finally {
            em.close();
        }

        return result;
    }

    static boolean createBasket(){
        EntityManager em = JpaUtils.getEmf();
        boolean result = false;
        Scanner sc = new Scanner(System.in);

        String basket_code;

        System.out.println("Please enter the basket code: ");
        basket_code = sc.nextLine();

        try {
            em.getTransaction().begin();
            Basket basket = new Basket(basket_code);
            em.persist(basket);
            em.getTransaction().commit();
            logger.info("le panier {} a bien ete creer et a pour code  ",basket_code);
            result = true;
        }catch (Exception e){
            em.getTransaction().rollback();
            logger.error("erreur lors de la creation du basket {}",e.getMessage());
            Sentry.captureException(e);
        }finally {
            em.close();
        }

        return result;
    }

    static boolean createProductBasket(){

       EntityManager em = JpaUtils.getEmf();
       boolean result = false;
       Scanner sc = new Scanner(System.in);
       int id_basket;
       int id_product;
       int quantity=0;
       Basket basket = null;
       Product product = null;

        System.out.println("Please enter the basket id: ");
        id_basket = sc.nextInt();
        basket = em.find(Basket.class, id_basket);
        if(basket == null){
            logger.info("le panier avec l'id {} n' exite pas ",id_basket);
            return false;
        }else{
            System.out.println("Please enter the product id: ");
            id_product = sc.nextInt();
            product = em.find(Product.class, id_product);
            if(product == null){
                logger.info("le produit avec l'id  {} n'a pas ete trouver ",id_product);
                return false;
            }else{

                System.out.println("Please enter the quantity: ");
                quantity = sc.nextInt();

                while (quantity<0){
                    System.out.println("Please enter the quantity: ");
                    quantity = sc.nextInt();
                }
                double part2 = Math.random()*100+(Math.random()*10)+(2*quantity);
                String code_product_basket= part2+"codePP";
                try {
                    em.getTransaction().begin();
                    ProductBasket productBasket = new ProductBasket(code_product_basket,product,basket,quantity);
                    em.persist(productBasket);
                    em.getTransaction().commit();
                    result = true;
                    message(true,"ProductBasket",false,"");
                    logger.info("class d'association entre produit et panier creer");
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    logger.error("Exception d'association entre produit et panier creer {}", e.getMessage());
                    Sentry.captureException(e);
                }finally {
                    em.close();
                }

            }

        }
            return result;
    }

    static void message(boolean bool, String typeOfObject, boolean ispersonnalMessage,String PersonnalMessage){

       if(ispersonnalMessage && PersonnalMessage!=""){
           System.out.println(PersonnalMessage);
       }else{
           if(bool){
               System.out.println("new "+typeOfObject+" added successfully");
           }else{
               System.out.println(typeOfObject+" not added ");
           }
       }

    }

    public static void main(String[] args) {
        logger.info("lancement de l'application");
        int option = menu();
        System.out.println(option);
        EntityManager em = JpaUtils.getEmf();
        EntityTransaction et = em.getTransaction();
        boolean result ;
        try {
            switch (option){
                case 1:
                    logger.info("choix de l'option {}",1);
                    result = createProduct();
                   // message(result, "Product",false,"");
                    break;
                    case 2:
                        logger.info("choix de l'option {}",2);
                        result = createBasket();
                        //message(result, "Basket",false,"");
                        break;
                        case 3:
                            logger.info("choix de l'option {}",2);
                            result = createProductBasket();
                            break;
                default :System.out.println("Please enter a number between 1 and 4");
            }

        } catch (Exception e) {
            logger.error("erreur lors de l'exuction des use case  {}",e.getMessage());
        }finally {
            em.close();
        }
    }
}