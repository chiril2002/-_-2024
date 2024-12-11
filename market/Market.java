package market;

import market.behaviour.MarketBehaviour;
import market.behaviour.QueueBehaviour;
import market.models.Customer;
import market.models.Order;
import market.exceptions.EmptyQueueException;

import java.text.NumberFormat.Style;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Market implements QueueBehaviour, MarketBehaviour{
    private Queue<Customer> queue = new ConcurrentLinkedQueue<>();
    private Queue<Order> orders = new  ConcurrentLinkedQueue<>();



    @Override
    public void addPersonToQueue(String person ){
        queue.add(new Customer( person));
        System .out.println( person +  " добавлен(а) в очередь.");
    }


    @Override 
    public void removePersonFromQueue(){
        if (!queue.isEmpty()) {
            Customer person = queue.poll();
            System.out.println(person.getName()+  " вышел(а) из очереди.");
        }else {
            System.out.println("Нет людей в очереди.");
        }
    }



    @Override
    public void takeOrder(String  order){
        orders.add(new Order(order));
        System.out.println("Заказ \"" + order + "\" принят.");

    }


    @Override 
    public String deliverOrder(){
        if (!orders.isEmpty()){
            Order order = orders.poll();
            System.out.println("Заказ \"" + order.getDescription() + "\" доставлен.");
            return order.getDescription();

        }else {
            System.out.println("Нет заказов для доставки.");
            return null;
        }
    }



    public void update() {
        System.out.println( "\n[Обновление состояния магазина]");
        if (!queue.isEmpty() && !orders.isEmpty()){

            Customer currentperson = queue.peek();
            Order currentOrder = orders.peek();
            System.out.println(currentperson.getName() + " получает заказ \"" + currentOrder.getDescription() + "\".");
            deliverOrder();
            removePersonFromQueue();
        }else {
            System.out.println("Нет клиентов или заказов для обработки.");
        }
        
    }
    public static void main(String[] args){
        Market market = new Market();


        market.addPersonToQueue("Alice");
        market.addPersonToQueue("Bob");


        market.takeOrder("Фрукты");
        market.takeOrder("Овощи");


        market.update();
        market.update();

        market.update();

    }




 

    
}










































// package market;

// import market.behaviour.QueueBehaviour;
// import market.behaviour.MarketBehaviour;
// import market.models.Customer;
// import market.models.Order;
// import market.exceptions.EmptyQueueException;

// import java.util.Queue;
// import java.util.concurrent.ConcurrentLinkedQueue;

// public class Market implements QueueBehaviour, MarketBehaviour {
//     private Queue<Customer> queue = new ConcurrentLinkedQueue<>();
//     private Queue<Order> orders = new ConcurrentLinkedQueue<>();

//     @Override
//     public void addPersonToQueue(String person) {
//         queue.add(new Customer(person));
//         System.out.println(person + " a fost adăugat(ă) la coadă.");
//     }

//     @Override
//     public void removePersonFromQueue() {
//         if (!queue.isEmpty()) {
//             Customer person = queue.poll();
//             System.out.println(person.getName() + " a părăsit coada.");
//         } else {
//             System.out.println("Nu există persoane în coadă.");
//         }
//     }

//     @Override
//     public void takeOrder(String order) {
//         orders.add(new Order(order));
//         System.out.println("Comanda \"" + order + "\" a fost primită.");
//     }

//     @Override
//     public String deliverOrder() {
//         if (!orders.isEmpty()) {
//             Order order = orders.poll();
//             System.out.println("Comanda \"" + order.getDescription() + "\" a fost livrată.");
//             return order.getDescription();
//         } else {
//             System.out.println("Nu există comenzi de livrat.");
//             return null;
//         }
//     }

//     public void update() {
//         System.out.println("\n[Actualizare stare magazin]");
//         if (!queue.isEmpty() && !orders.isEmpty()) {
//             Customer currentPerson = queue.peek();
//             Order currentOrder = orders.peek();
//             System.out.println(currentPerson.getName() + " primește comanda \"" + currentOrder.getDescription() + "\".");
//             deliverOrder();
//             removePersonFromQueue();
//         } else {
//             System.out.println("Nu există clienți sau comenzi de procesat.");
//         }
//     }

//     public static void main(String[] args) {
//         Market market = new Market();

//         market.addPersonToQueue("Alice");
//         market.addPersonToQueue("Bob");

//         market.takeOrder("Fructe");
//         market.takeOrder("Legume");

//         market.update();
//         market.update();

//         market.update();
//     }
// }
