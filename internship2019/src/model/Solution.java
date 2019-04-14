package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    public ArrayList<ATM> listATMs;
    public ArrayList<CreditCard> listCredidCards;
    public ArrayList<WalkingTime> listWalkingTimes;
    public int withdrawingsum = 7500;

    public String currentDate = "19.03.2019";
    public LocalTime currentTime = LocalTime.parse("11:30");

    public String finishDate = "19.03.2019";
    public LocalTime finishTime = LocalTime.parse("14:30");

    public Solution(){}

    public void loadData()
    {
        listATMs = new ArrayList<>();
        listCredidCards = new ArrayList<>();
        listWalkingTimes = new ArrayList<>();

        ATM atm = new ATM();

        atm = new ATM("ATM 1",LocalTime.parse("12:00"),LocalTime.parse("18:00"));
        listATMs.add(atm);

        atm = new ATM("ATM 2",LocalTime.parse("10:00"),LocalTime.parse("17:00"));
        listATMs.add(atm);

        atm = new ATM("ATM 3",LocalTime.parse("22:00"),LocalTime.parse("12:00"));
        listATMs.add(atm);

        atm = new ATM("ATM 4",LocalTime.parse("17:00"),LocalTime.parse("01:00"));
        listATMs.add(atm);


        CreditCard cc;

        cc = new CreditCard("SILVER", 0.2f,4500,"23.05.2020",20000);
        listCredidCards.add(cc);

        cc = new CreditCard("GOLD", 0.1f,3000,"15.08.2018",25000);
        listCredidCards.add(cc);

        cc = new CreditCard("PLATINUM", 0f,4000,"20.03.2019",3000);
        listCredidCards.add(cc);

        User u = new User("User starting point");

        WalkingTime wt;

        wt = new WalkingTime(u,listATMs.get(0),5);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(u,listATMs.get(1),60);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(u,listATMs.get(2),30);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(u,listATMs.get(3),45);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(0),listATMs.get(1),40);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(0),listATMs.get(3),45);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(1),listATMs.get(2),15);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(2),listATMs.get(0),40);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(2),listATMs.get(3),15);
        listWalkingTimes.add(wt);

        wt = new WalkingTime(listATMs.get(3),listATMs.get(1),30);
        listWalkingTimes.add(wt);

        withdrawingsum = 7500;
        //withdrawingsum = 10050;

        currentDate = "19.03.2019";
        currentTime = LocalTime.parse("11:30");

        finishDate = "19.03.2019";
        finishTime = LocalTime.parse("14:00");
//        finishTime = LocalTime.parse("19:00");
    }

    public static Comparator<CreditCard> cmp(CreditCard c1, CreditCard c2)
    {
        return new Comparator<CreditCard>() {
            @Override
            public int compare(CreditCard o1, CreditCard o2) {
                if (o1.getFee()<=o2.getFee())
                    return 1;
                else return 0;
            }
        };
    }

    public void organizeData()
    {
        //we want to eliminate the invalid credit cards and to sort the remaining ones after the fees so that it will be easier to work with them
        ArrayList<CreditCard> valid=new ArrayList<>();

        for (CreditCard el : listCredidCards)
        {
            if (Integer.parseInt(el.getExprdate().split(Pattern.quote("."))[2])>=Integer.parseInt(currentDate.split(Pattern.quote("."))[2]))
                if (Integer.parseInt(el.getExprdate().split(Pattern.quote("."))[1])>=Integer.parseInt(currentDate.split(Pattern.quote("."))[1]))
                    if (Integer.parseInt(el.getExprdate().split(Pattern.quote("."))[0])>Integer.parseInt(currentDate.split(Pattern.quote("."))[0]))
                        valid.add(el);
        }
        listCredidCards = valid;

        CreditCard aux;
        for (int i=0;i < listCredidCards.size()-1;i++)
            for (int j=i+1; j < listCredidCards.size();j++)
            {
                if (listCredidCards.get(i).getFee()> listCredidCards.get(j).getFee())
                {
                    aux = listCredidCards.get(i);
                    listCredidCards.set(i,listCredidCards.get(j));
                    listCredidCards.set(j,aux);
                }
            }

            //we also sort he atms according to their oppening time
            ATM a;
        for (int i=0;i < listATMs.size()-1;i++)
            for (int j=i+1; j < listATMs.size();j++)
            {
                if (listATMs.get(i).getOpeningtime().getHour() > listATMs.get(j).getOpeningtime().getHour() )
                {

                    a = listATMs.get(i);
                    listATMs.set(i,listATMs.get(j));
                    listATMs.set(j,a);
                }
                else if (listATMs.get(i).getOpeningtime().getHour() == listATMs.get(j).getOpeningtime().getHour() && listATMs.get(i).getOpeningtime().getMinute() > listATMs.get(j).getOpeningtime().getMinute())
                {
                    a = listATMs.get(i);
                    listATMs.set(i,listATMs.get(j));
                    listATMs.set(j,a);
                }
            }
    }

    /**
     * This function gets a list with all the variants that a user can choose to go having the 'from' a given string
     * @param from
     * @return
     */
    public List<WalkingTime> getAllWalkingTimeHavingFrom(String from)
    {
        return listWalkingTimes.stream().filter(el->el.getFrom().getName()==from).collect(Collectors.toList());
    }

    /**
     * Gets an object of type ATM using a given string (the name of the ATM) to search for it.
     * @param a
     * @return
     */
    public ATM getATM(String a)
    {
        for (ATM el : listATMs)
        {
            if (el.getName() == a) return el;
        }
        return null;
    }

    /**
     * Searches for an ATM to see if it exists already in a given list of ATMs
     * @param list
     * @param item
     * @return
     */
    public boolean itExist(ArrayList<ATM> list, ATM item)
    {
        return list.contains(item);
    }

    /**
     * The solution is computed.
     * @return
     */
    public List<ATM> getAtmsRoute()
    {
        //here we load the data and organize it
        loadData();
        organizeData();

        // Here we have many list of states in order to preserve the data in case we want to get back and find another route
        ArrayList<WalkingTime> userOpt = new ArrayList<>(getAllWalkingTimeHavingFrom("User starting point"));
        ArrayList<ATM> solution=new ArrayList<>();
        ArrayList<ArrayList<CreditCard>> stateOfCreditCards=new ArrayList<>();
        ArrayList<Integer> indexLastAdded = new ArrayList<>();
        ArrayList<Integer> stateWithdrawingsum = new ArrayList<>();
        stateWithdrawingsum.add(withdrawingsum);
        ArrayList<LocalTime> stateCurrentTime = new ArrayList<>();
        stateCurrentTime.add(currentTime);

        stateOfCreditCards.add(listCredidCards);
        int atmsum=5000;
        int index = 0;

        while (index<userOpt.size())
        {
            atmsum=5000;
            if (withdrawingsum==0) break;

            //verify the validity of the time
            if (getATM(userOpt.get(index).getTo().getName()).getOpeningtime().isBefore(finishTime) && currentTime.plusMinutes(userOpt.get(index).getDuration()).isBefore(finishTime))
            {

                //withdrawing money from atm
                while (withdrawingsum != 0 && atmsum != 0) {
                    for (int i = 0; i < listCredidCards.size() && atmsum!=0; i++) {
                        //if the amount of a credit card is 0 we want to go to the next credit card that has money
                        //the data is already organized and this list of credit card has only valid ones
                        if (listCredidCards.get(i).getAmount() == 0) continue;


                        //we check if the sum we want to withdraw from a credit card is less than the sum of money the atm has
                        // and also if the amount of money from a credit card is less that than the withdrawingsum
                        if (listCredidCards.get(i).getAmount() <= withdrawingsum && listCredidCards.get(i).getAmount() <= atmsum) {
                            //we take into account the Withdrawing limit of every Creditcard
                            if (listCredidCards.get(i).getWithdrawlimit() <= withdrawingsum)
                            {
                                withdrawingsum -= listCredidCards.get(i).getWithdrawlimit();
                                atmsum -= listCredidCards.get(i).getWithdrawlimit();
                                listCredidCards.get(i).setAmount(listCredidCards.get(i).getAmount()-listCredidCards.get(i).getWithdrawlimit());
                                listCredidCards.get(i).setWithdrawlimit(0);
                            }
                            else
                            {
                                withdrawingsum -= listCredidCards.get(i).getAmount();
                                listCredidCards.get(i).setWithdrawlimit(listCredidCards.get(i).getWithdrawlimit() - withdrawingsum);
                                atmsum -= listCredidCards.get(i).getAmount();
                                listCredidCards.get(i).setAmount(0);
                            }
                        } else {
                            if (listCredidCards.get(i).getAmount() <= atmsum) {
                                //check the withdrawlimit
                                if (listCredidCards.get(i).getWithdrawlimit() <= withdrawingsum) {
                                    withdrawingsum -= listCredidCards.get(i).getWithdrawlimit();
                                    atmsum -= listCredidCards.get(i).getWithdrawlimit();
                                    listCredidCards.get(i).setAmount(listCredidCards.get(i).getAmount()-listCredidCards.get(i).getWithdrawlimit());
                                    listCredidCards.get(i).setWithdrawlimit(0);
                                }
                                else {
                                    listCredidCards.get(i).setAmount(listCredidCards.get(i).getAmount() - withdrawingsum);
                                    listCredidCards.get(i).setWithdrawlimit(listCredidCards.get(i).getWithdrawlimit() - withdrawingsum);
                                    atmsum -= listCredidCards.get(i).getAmount();
                                    withdrawingsum = 0;
                                }
                            } else {
                                if (listCredidCards.get(i).getWithdrawlimit() <= atmsum) {
                                    listCredidCards.get(i).setAmount(listCredidCards.get(i).getAmount() - atmsum);
                                    withdrawingsum -= listCredidCards.get(i).getWithdrawlimit();
                                    atmsum -= listCredidCards.get(i).getWithdrawlimit();
                                    listCredidCards.get(i).setWithdrawlimit(0);
                                }
                                else {
                                listCredidCards.get(i).setAmount(listCredidCards.get(i).getAmount() - atmsum);
                                listCredidCards.get(i).setWithdrawlimit(listCredidCards.get(i).getWithdrawlimit() - atmsum);
                                withdrawingsum = withdrawingsum - atmsum;
                                atmsum = 0;
                                }
                                if (withdrawingsum<0) withdrawingsum=0;
                                atmsum = 0;
                                break;
                            }
                        }
                        if (withdrawingsum == 0) break;
                    }
                }

                //check if the ATM already exists in the list of solutions
                if (!itExist(solution,getATM(userOpt.get(index).getTo().getName())))
                {
                    //if not add the solution
                    solution.add(getATM(userOpt.get(index).getTo().getName()));
                    //save the states (in case we want to get back)
                    stateOfCreditCards.add(listCredidCards);
                    indexLastAdded.add(index);
                    stateWithdrawingsum.add(withdrawingsum);
                    if (currentTime.plusMinutes(userOpt.get(index).getDuration()).isBefore(getATM(userOpt.get(index).getTo().getName()).getOpeningtime()))
                    {
                        currentTime = getATM(userOpt.get(index).getTo().getName()).getOpeningtime();
                    }
                    else
                    {
                        currentTime = currentTime.plusMinutes(userOpt.get(index).getDuration());
                    }
                    stateCurrentTime.add(currentTime);
                }
//                else
//                {
//                    //if the solution exists we want to go back a step
//                    //go back
//                    if (solution.size()==1) {
//                        userOpt = new ArrayList<>(getAllWalkingTimeHavingFrom("User starting point"));
//
//                        index = indexLastAdded.get(indexLastAdded.size()-1);
//                        indexLastAdded.remove(indexLastAdded.size()-1);
//
//                        withdrawingsum = stateWithdrawingsum.get(stateWithdrawingsum.size()-1);
//                        stateWithdrawingsum.remove(stateWithdrawingsum.size()-1);
//
//                        currentTime = stateCurrentTime.get(stateCurrentTime.size()-1);
//                        stateCurrentTime.remove(stateCurrentTime.size()-1);
//
//                        solution.remove(solution.size()-1);
//                    }
//                    else
//                    {
//                        userOpt = new ArrayList<>(getAllWalkingTimeHavingFrom(solution.get(solution.size() - 1).getName()));
//                        index = indexLastAdded.get(indexLastAdded.size()-1);
//                        indexLastAdded.remove(indexLastAdded.size()-1);
//
//                        withdrawingsum = stateWithdrawingsum.get(stateWithdrawingsum.size()-1);
//                        stateWithdrawingsum.remove(stateWithdrawingsum.size()-1);
//
//                        currentTime = stateCurrentTime.get(stateCurrentTime.size()-1);
//                        stateCurrentTime.remove(stateCurrentTime.size()-1);
//
//                        solution.remove(solution.size()-1);
//                    }
//                }
            }
            else
            {

                //if the time is not valid we go back a step

                //we check if we arrived at the and of the list of options or if the withdrawingsum is still not 0
                if (withdrawingsum != 0 || index == userOpt.size()-1)
                {
                    //need to go back a step


                    if (solution.size()==1) {
                        userOpt = new ArrayList<>(getAllWalkingTimeHavingFrom("User starting point"));
                        index = indexLastAdded.get(indexLastAdded.size()-1);
                        indexLastAdded.remove(indexLastAdded.size()-1);

                        //we use the saved states in order to go back to the previous state (and continue the search)

                        withdrawingsum = stateWithdrawingsum.get(stateWithdrawingsum.size()-1);
                        stateWithdrawingsum.remove(stateWithdrawingsum.size()-1);

                        currentTime = stateCurrentTime.get(stateCurrentTime.size()-1);
                        stateCurrentTime.remove(stateCurrentTime.size()-1);

                        solution.remove(solution.size()-1);
                    }
                    else
                    {
                        userOpt = new ArrayList<>(getAllWalkingTimeHavingFrom(solution.get(solution.size() - 1).getName()));
                        index = indexLastAdded.get(indexLastAdded.size()-1);
                        indexLastAdded.remove(indexLastAdded.size()-1);

                        //we use the saved states in order to go back to the previous state (and continue the search)

                        withdrawingsum = stateWithdrawingsum.get(stateWithdrawingsum.size()-1);
                        stateWithdrawingsum.remove(stateWithdrawingsum.size()-1);

                        currentTime = stateCurrentTime.get(stateCurrentTime.size()-1);
                        stateCurrentTime.remove(stateCurrentTime.size()-1);

                        solution.remove(solution.size()-1);
                    }
                }
            }
            if (withdrawingsum == 0) break;
            index++;
            if (atmsum==0) {
                //whne we withdraw all the money from an atm we search for the next one so we generate a new list of userOpt that have the 'from' the 'to' of the last added solution
                userOpt= new ArrayList<>(getAllWalkingTimeHavingFrom(userOpt.get(index-1).getTo().getName()));
                index=0;
            }
        }

        //if at the end of the while we still have money left to withdraw we throw an exception with a message
        if (withdrawingsum!=0) throw new RuntimeException("Cannot find a route.");
        return solution;

    }
}
