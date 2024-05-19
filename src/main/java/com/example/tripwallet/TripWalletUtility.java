package com.example.tripwallet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReaderBuilder;
import org.springframework.context.annotation.Bean;


public class TripWalletUtility {

    private static final String COMMA_DELIMITER = ",";

    public void insertTopup(TopUp t, String CSV_FILE){
        File file = new File(CSV_FILE);
        try {
            List<String[]> existingData = readCSVFile(CSV_FILE);
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { t.getTripId()+"", t.getDate(),t.getTransType(),t.getLastFour(), t.getCardType(),t.getTopUpAmount()+"", t.getPointsAmount()+"",t.getService(),t.getItem()};
            if(null == existingData || existingData.isEmpty()){
                existingData = new ArrayList<String[]>();
            }
            existingData.add(header);
            writer.writeAll(existingData);
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    public List<List<String>> getTransactionDate(String CSV_FILE) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            List<List<String>> records = reader.lines()
                    .map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                    .collect(Collectors.toList());
            return records;
        }
    }
    public List<String[]> readCSVFile(String CSV_FILE) throws IOException {
        List<String[]> allData = null;
        try{
            FileReader filereader = new FileReader(CSV_FILE);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReader(filereader);
            allData = csvReader.readAll();
            csvReader.close();
            filereader.close();
        }catch(Exception e){
            //
        }
        return allData;

    }
    public List<WalletSummary> fetchSummary(String CSV_FILE) throws IOException {
        List<String[]> allData = readCSVFile(CSV_FILE);
        Map<String, String> summary = new HashMap<>();
        double cashUsed = 0;
        long pointsUsed = 0;
        double totalCashAuthorized = 0;
        long totalPointsAuthorized = 0;
        double cashBalance = 0;
        long pointsBalance = 0;
        Map<String,TopupSummary> topupSummary = new HashMap<>();
        Map<String,TopupSummary> topupSummaryPoints = new HashMap<>();
        List<TopUp> transactions = new ArrayList<>();
        for (String[] record : allData) {

            if(!record[2].isEmpty() || !record[2].isBlank()){
                if(record[2].equals("CASH") && !record[7].equals("WALLET")){
                    cashUsed = cashUsed + (Double.parseDouble(record[5]));
                } else if (record[2].equals("POINTS") && !record[7].equals("WALLET")) {
                    pointsUsed = pointsUsed + Long.parseLong(record[6]);
                }
                if(record[2].equals("CASH") && record[7].equals("WALLET")){
                    totalCashAuthorized = totalCashAuthorized + (Double.parseDouble(record[5]));
                }
                if(record[2].equals("POINTS") && record[7].equals("WALLET")){
                    totalPointsAuthorized = totalPointsAuthorized + (Long.parseLong(record[6]));
                }
                if(record[2].equals("CASH") && !record[7].equals("WALLET")){
                    if(topupSummary.containsKey(record[3])){
                        TopupSummary v = (TopupSummary) topupSummary.get(record[3]);
                        double d=v.getAuthorized()+Double.parseDouble(record[5]);
                        v.setAuthorized(d);
                        v.setCashTrans(true);
                        topupSummary.put(record[3],v);
                    }else{

                        TopupSummary value = new TopupSummary(record[4], record[3], Double.parseDouble(record[5]), record[2], Long.parseLong(record[6]));
                        value.setCashTrans(true);
                        topupSummary.put(record[3], value);
                    }

                }
                if(record[2].equals("POINTS") && !record[7].equals("WALLET")){
                    if(topupSummary.containsKey(record[2])){
                        TopupSummary v = (TopupSummary) topupSummary.get(record[3]);
                        long d=v.getPointsAuthorized()+Long.parseLong(record[6]);
                        v.setPointsAuthorized(d);
                        v.setPointsTrans(true);
                        topupSummaryPoints.put(record[2],v);
                    }else{
                        TopupSummary value = new TopupSummary(record[4], record[3], Double.parseDouble(record[5]), record[2], Long.parseLong(record[6]));
                        value.setPointsTrans(true);
                        topupSummaryPoints.put(record[2], value);
                    }

                }
            }

            TopUp e = new TopUp(Long.parseLong(record[0]), record[1], record[2], record[3], record[4], Double.parseDouble(record[5]), Long.parseLong(record[6]), record[7], record[8]);
            if(Long.parseLong(record[6]) > 0){
                e.setPointsTras(true);
            }
            if(Double.parseDouble(record[5]) > 0){
                e.setCashTrans(true);
            }
            transactions.add(e);

        }
        cashBalance = totalCashAuthorized - cashUsed;
        pointsBalance = totalPointsAuthorized - pointsUsed;
        WalletSummary w = new WalletSummary();
        w.setCashUsed(cashUsed);
        w.setCashBalance(cashBalance);
        w.setPointsUsed(pointsUsed);
        w.setPointsBalance(pointsBalance);
        w.setTotalPointsAuthorized(totalPointsAuthorized);
        w.setTotalCashAuthorized(totalCashAuthorized);
        if(!(pointsUsed > 0)){
            w.setHasNoPoints(true);
        }
        List<WalletSummary> lw= new ArrayList<WalletSummary>();
        List<TopupSummary> ts = new ArrayList<TopupSummary>(topupSummary.values());
        List<TopupSummary> tsp = new ArrayList<TopupSummary>(topupSummaryPoints.values());
        w.setTopupSummaryList(ts);
        w.setTopupSummaryPoints(tsp);
        w.setTransactions(transactions);
        lw.add(w);

        return lw;
    }
    public String buildWallet(String fn){
        if(null != fn){
            File f = new File(fn);
            if(f.exists()){
                return fn;
            }
        }
         String fieName = UUID.randomUUID().toString()+".csv";
        TopUp top = new TopUp(356784,"MAY 16","CASH","4567","visa",320.50,0,"WALLET","TOPUP");
        TopUp top1 = new TopUp(356784,"MAY 16","CASH","4567","visa",320.50,0,"STAY","Resort King Guest Room");
        insertTopup(top,fieName);
        insertTopup(top1,fieName);
        return fieName;
    }
}
