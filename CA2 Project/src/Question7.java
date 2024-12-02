// Example scenario because I keep forgetting what I am meant to do:
// Bought one block of 100 shares for €20, bought a second block for another 100 at €10
// User wants to sell 175 blocks for €30
// Formula: Gain = (Sell price - Buy price) * Quantity
// So take 100 shares from first block: (30-20) * 100 = €1000
// Take remaining 75 shares from second block: (30-10) * 75 = €750
// Total gain = €1750

import java.util.*;

public class Question7 {

    static Scanner scanner = new Scanner(System.in);

    static Map<String, Queue<Block>> companyShares = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("STOCK SIMULATOR");
        menuOptions();
    }

    public static void menuOptions() {
        String[] options = new String[4];
        options[0] = "Buy shares";
        options[1] = "Sell shares";
        options[2] = "View shares";
        options[3] = "Quit application";

        UtilityClass.menuOptions(options);
        int choice = UtilityClass.validateRange(1,4);

        if(choice == 1) {
            buyMenu();
        }
        else if(choice == 2) {
            sellMenu();
        }
        else if(choice == 3) {
            displayAllBlocks();
        }
        else {
            System.out.println("Ending session...\nDone! Goodbye.");
        }
    }

    public static void buyMenu() {
        Queue<Block> boughtShares = new ArrayDeque<>();

        System.out.println("Enter the company that you would like to buy shares from:");
        String company = scanner.next();

        System.out.println("Enter amount of shares you would like to buy below:");
        int sharesToBuy = UtilityClass.validateInt();

        System.out.println("Enter the price for these shares (rounded up):");
        int buyPrice = UtilityClass.validateInt();

        // if the map already has the company, add it to the queue for that company
        if(companyShares.containsKey(company)) {
            companyShares.get(company).add(new Block(sharesToBuy, buyPrice));

            System.out.println(sharesToBuy+ " new shares successfully added to '" +company+ "' for €" +buyPrice+ "! Returning to main menu...\n");
        }
        // otherwise, add the new key (company) to the map and add the new block to the queue
        else {
            // add the new block to the queue with the given shares and price
            boughtShares.add(new Block(sharesToBuy, buyPrice));

            companyShares.put(company, boughtShares);

            System.out.println(sharesToBuy+ " shares successfully bought from '" +company+ "' for €" +buyPrice+ "! Returning to main menu...\n");
        }

        menuOptions();
    }

    public static void sellMenu() {
        System.out.println("Enter the company that you would like to sell from:");
        String company = scanner.next();

        if(!companyShares.containsKey(company)) {
            System.out.println("You have not bought anything from this company. Returning to main menu...\n");
            menuOptions();
        }
        else {
            System.out.println("Enter amount of shares you would like to sell below:");
            int sharesToSell = UtilityClass.validateInt();

            System.out.println("Enter the sell price for these shares (rounded up):");
            int sellPrice = UtilityClass.validateInt();

            int gain = 0, blocksSoldOut = 0;
            sellSharesFromBlocks(sharesToSell, gain, sellPrice, blocksSoldOut, company);

            menuOptions();
        }
    }

    public static void displayAllBlocks() {
        if(!companyShares.isEmpty()) {
            // Map.Entry allows me to be able to print out the maps keys and values by doing companyShares.entrySet()
            for (Map.Entry<String, Queue<Block>> companyShares : companyShares.entrySet()) {
                String company = companyShares.getKey();
                Queue<Block> block = companyShares.getValue();

                System.out.println("Company: " +company);
                for(Block shares : block) {
                    System.out.println("|| " +shares.getQuantity()+ " shares for €" +shares.getPrice()+ " ||");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("No stocks have been bought yet or all stocks sold!\n");
        }

        menuOptions();
    }

    public static void sellSharesFromBlocks(int sharesToSell, int gain, int sellPrice, int blocksSoldOut, String company) {
        int copyOfSharesToSell = sharesToSell;
        // to avoid having to write companyShares.get(company) all the time
        Queue<Block> companyBlockToSell = companyShares.get(company);

        while(sharesToSell > 0 && !companyBlockToSell.isEmpty()) {
            Block currentBlock = companyBlockToSell.peek();
            int sharesInCurrentBlock = currentBlock.getQuantity();

            // will not continue with selling rest of sharesToSell if quantity of currentBlock is less than shares left to sell (sharesToSell)
            if(companyBlockToSell.size() == 1 && currentBlock.getQuantity() < sharesToSell) {
                System.out.println("Cannot sell remaining " +sharesToSell+ " share(s) but managed to sell " +(copyOfSharesToSell - sharesToSell)+ " shares.");
                break;
            }

            // if sharesToSell < sharesInCurrentBlock, sell that. Otherwise, sell amount will be sharesInCurrentBlock
            int availableSharesToSell = Math.min(sharesToSell, sharesInCurrentBlock);

            gain += (sellPrice - currentBlock.getPrice()) * availableSharesToSell;

            // reduce the quantity of chosen block within q7andq8.Block class
            currentBlock.sellShares(availableSharesToSell);
            // reduce the share by the amount the user is able to sell for the current block
            sharesToSell -= availableSharesToSell;

            // remove the currentBlock if its share count is now 0
            if(currentBlock.getQuantity() == 0) {
                companyBlockToSell.remove();
                blocksSoldOut++;
            }
        }

        if(gain > 0) {
            System.out.println("Gained total of €" + gain + " from shares and have fully sold out " +blocksSoldOut+ " block(s) from " +company+ ". Returning to main menu...\n");
        }
        else {
            System.out.println("No profit gained from selling these stocks and have fully sold out " +blocksSoldOut+ " block(s) from " +company+ ". Returning to main menu...\n");
        }
    }
}
