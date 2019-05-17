package com.tekitsolutions.remindme.Utils;

import android.text.InputType;

import com.tekitsolutions.remindme.Model.ProvidersInfo;

import java.util.HashMap;

public class ProviderStatic {

    public static HashMap<String, ProvidersInfo> providersInfoHashMap;
    private static ProviderStatic mProviderSatic = new ProviderStatic();

    static {
        providersInfoHashMap = new HashMap<String, ProvidersInfo>();
        providersInfoHashMap.put("Airtel Digital TV",
                new ProvidersInfo("Customer ID",
                        "To Know The CustomerID,press The Menu Button on your remote and choose My ParticularPayment",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("Dis TV", new ProvidersInfo("Viewing Card Number",
                "Please enter 11 digit Viewing Card Number,Starting with 0", InputType.TYPE_CLASS_NUMBER, 11, -1, 0));
        providersInfoHashMap.put("Reliance Digital TV",
                new ProvidersInfo("Smart Card Number", "   Please enter 12 digit Viewing Card Number",
                        InputType.TYPE_CLASS_NUMBER, 12, -1, 2));
        providersInfoHashMap.put("Sun Direct",
                new ProvidersInfo("Smart Card Number", "Please enter your Sun Direcet Smart Card Number",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Tata Sky",
                new ProvidersInfo("Registered Mobile Number or Sbsrciber ID", "please Enter your 10 digit Registred Mobile No. or Your 10 digit Sbcriver ID starting with 1",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, 0));
        providersInfoHashMap.put("Videocon D2H",
                new ProvidersInfo("Subscriber ID", "To Know your Subscriber ID,SMS 'ID' from your" +
                        "registred mobile number to 9212012299",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));

        // here for Broad band validation..................

        providersInfoHashMap.put("ACT Broadband",
                new ProvidersInfo("ParticularPayment No./UserName", "Please enter a valid ParticularPayment No. or UserName",
                        InputType.TYPE_CLASS_TEXT, -1, -1, -1));
        providersInfoHashMap.put("Airtel Landline",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Connect BroadBand",
                new ProvidersInfo("Directory Number", "Please enter your Directory Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Hathway BroadBand",
                new ProvidersInfo("Customer ID", "Please Enter Valid Customer ID",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("MTNL Delhi",
                new ProvidersInfo("Telephone Number without STD code", "Please enter your 8 digit Telephone Number",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("MTNL Mumbai",
                new ProvidersInfo("Telephone Number", "Please Enter Valid 8 digit Telephone number(eg. 88778926)",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("Reliance",
                new ProvidersInfo("MobileNumber/Telephone Number", "Please enter valid Mobile/Landline Number",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Tata DoCoMo CDMA",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Tata DoCoMo GSM",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));
        providersInfoHashMap.put("Tikona Digital Networks Pvt. Ltd.",
                new ProvidersInfo("Service ID", "Please enter a valid Service id",
                        InputType.TYPE_CLASS_NUMBER, -1, -1, -1));


        //landline_provider................

        providersInfoHashMap.put("Airtel Landline",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));
        providersInfoHashMap.put("BSNL Landline",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 10, 1, -1));
        providersInfoHashMap.put("Tata DoCoMo GSM",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));
        providersInfoHashMap.put("MTNL Delhi",
                new ProvidersInfo("Telephone Number without STD code", "Please enter your 8 digit Telephone Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("MTNL Mumbai",
                new ProvidersInfo("Telephone Number", "Please Enter Valid 8 digit Telephone number(eg. 88778926)",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("Reliance",
                new ProvidersInfo("MobileNumber/Telephone Number", "Please enter valid Mobile/Landline Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 5, -1));
        providersInfoHashMap.put("Tata Docomo CDMA",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        //Insurance Provider..............

        providersInfoHashMap.put("Bharti Axa Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 11 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("Canara HSBC OBC Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("DHFL Pramerica Life Insurance Co. Ltd",
                new ProvidersInfo("Policy Number", "Please enter your 8 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("ICICI Prudential Life Insurance",
                new ProvidersInfo("Policy Number", "Please enter your 8 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("IDBI Federal Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("IndiaFirst Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 8-16 digit/character Policy Number",
                        InputType.TYPE_CLASS_TEXT, 16, 8, -1));
        providersInfoHashMap.put("Max Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 9 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("SBI Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 11 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("TATA Life Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));


        //No Validations found

        /*
        providersInfoHashMap.put("Reliance Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));
        providersInfoHashMap.put("Birla Sun Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));

        providersInfoHashMap.put("Kotak Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));
                        providersInfoHashMap.put("PNB MetLife Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,1,20,-1));

        providersInfoHashMap.put("Sahara Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));*/


        // Car Insurance Provider Validation............
        providersInfoHashMap.put("Bajaj Alianz Car Insuance",
                new ProvidersInfo("Policy Number", "Please Enter 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Bharti AXA Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Cholamandalam Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Future Generali Car Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("HDFC Ergo Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("IFFCO Tokio Car Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Liberty Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("National Insurance Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("New India Assurance Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Oriental Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Raheja QBE Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Reliance General Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Royal Sundaram Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("SBI Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ShriRam Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("TATA AIG Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("United India Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Universal Sompo Car Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Bike Insurance Provider Validation...........
        providersInfoHashMap.put("Bajaj Alianz Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Bharti AXA Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("HDFC Ergo Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("IFFCO Tokio Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Reliance Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Universal Sompo Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Royal Sundaram Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("New India Assurance Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Liberty Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("National Insurance Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Oriental Insurance Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("SBI Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ShriRam Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("TATA AGI Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("United India Two Wheeler Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Health Insurance Provider Validation............
        providersInfoHashMap.put("Bajaj Alianz Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Bharti AXA Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Cholamandalam Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Cigna TTK Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Future Generali Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("HDFC Ergo Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("IFFCO Tokio Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Liberty Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("LIC Jivan Arogya Plan(Health Insurance)",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Max Bupa Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("National Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("New India Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Oriental Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Raheja QBE Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Reliance Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Religare Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Royal Sundaram Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("SBI Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Star Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("TATA AIG Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("United India Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Universal Sompo Health Insurance",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Term Insurance Provider Validation......

        providersInfoHashMap.put("Aegon Life Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Aviva Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Bajaj Alianz Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Bharti AXA Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Birla Sun Life Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Canara HSBC Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("DHFL Primerica Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("HDFC Life Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ICICI Prudential Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("IDBI Federal Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("IndiaFirst Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Kotak Life Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("LIC Term Insurance Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Max Life Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("PNB MetLife Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("Sahara Life Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("SBI Life Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ShriRam Life Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("TATA AIA Term Plans",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // gas Provider.......................
        providersInfoHashMap.put("Adani Gas",
                new ProvidersInfo("Customer ID", "Please enter your 10 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Aavantika Gas Ltd.",
                new ProvidersInfo("Customer ID", "Please enter your 10-16 digit Customer ID",
                        InputType.TYPE_CLASS_TEXT, 16, 10, -1));

        providersInfoHashMap.put("Central UP Gas",
                new ProvidersInfo("Customer ID", "Please enter your 1-20 digit Customer ID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("Charotar Gas Sahakari Mandali Limited",
                new ProvidersInfo("Customer ID", "Please enter your 1-5 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 5, 1, -1));

        providersInfoHashMap.put("Gail Gas Limited",
                new ProvidersInfo("Valid BP No.", "Please enter your 10 digit numeric BP no",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Gujarat Gas",
                new ProvidersInfo("Customer ID", "Please enter valid 9-12 digit Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 9, -1));

        providersInfoHashMap.put("Haryana City Gas - Kapil Chopra Enterprise",
                new ProvidersInfo("CRN Number", "Please enter 8-12 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Indian Oil Adani Gas Pvt Ltd",
                new ProvidersInfo("Customer ID", "Please enter fixed 10 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));


        providersInfoHashMap.put("Indraprasth Gas",
                new ProvidersInfo("BP Number", "Please enter your 10 digit numeric BP number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Mahanagar Gas",
                new ProvidersInfo("CA Number", "Please enter your 12 digit numeric CA number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Maharastra Natural Gas Limited",
                new ProvidersInfo("BP Number", "Please enter 7-10 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));


        providersInfoHashMap.put("Sabarmati Gas Limited (SGL)",
                new ProvidersInfo("Custom ID", "Please enter your 12 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Siti Energy",
                new ProvidersInfo("ARN Number", "Please enter your 7-9 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 9, 7, -1));

        providersInfoHashMap.put("Tripura Natural Gas Company Ltd",
                new ProvidersInfo("CONSUMER NO.", "Please enter valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("Unique Central Piped Gases Pvt Ltd (UCPGPL)",
                new ProvidersInfo("Consumer Number", "Please enter 8 digit CRN number",
                        InputType.TYPE_CLASS_TEXT, 8, 8, -1));

        providersInfoHashMap.put("Vadodara Gas Limited",
                new ProvidersInfo("Consumer Number", "Please enter 7 digit numeric Consumer number",
                        InputType.TYPE_CLASS_NUMBER, 7, 7, -1));

        // Water provider..........

        providersInfoHashMap.put("Bangalore Water Supply and Sewerage Board",
                new ProvidersInfo("RR No.", "Please enter valid RR No.",
                        InputType.TYPE_CLASS_TEXT, 8, 8, -1));

        providersInfoHashMap.put("Bhopal Municipal Corporation - Water",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 1, -1));


        providersInfoHashMap.put("Delhi Jal Board",
                new ProvidersInfo("K No.", "Please enter valid K No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("Greater Warangal Municipal Corporation - Water",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 7, -1));

        providersInfoHashMap.put("Gwalior Municipal Corporation - Water",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 1, -1));

        providersInfoHashMap.put("Haryana Urban Development Authority - Water Bill",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 20, 2, -1));

        providersInfoHashMap.put("Hyderabad Metropolitan Water Supply and Seweral Board",
                new ProvidersInfo("CAN No.", "Please enter valid CAN No.",
                        InputType.TYPE_CLASS_TEXT, 25, 2, -1));

        providersInfoHashMap.put("Indore Municipal Corporation - Water",
                new ProvidersInfo("Service No", "Please enter valid Service No.",
                        InputType.TYPE_CLASS_NUMBER, 16, 6, -1));

        providersInfoHashMap.put("Jabalpur Municipal Corporation - Water",
                new ProvidersInfo("Service No.", "Please enter valid Service No.",
                        InputType.TYPE_CLASS_NUMBER, 16, 6, -1));

        providersInfoHashMap.put("Ludhiana Municipal Corporation",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 1, -1));

        providersInfoHashMap.put("Municipal Corporation Jalandhar",
                new ProvidersInfo("UID", "Please enter valid UID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("Municipal Corporation of Gurugram",
                new ProvidersInfo("K No.", "Please enter valid K No.",
                        InputType.TYPE_CLASS_TEXT, 20, 7, -1));

        providersInfoHashMap.put("New Delhi Municipal Council (NDMC) - Water",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));

        providersInfoHashMap.put("PHED - Rajasthan",
                new ProvidersInfo("CID Code", "Please enter valid CID Code",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Pune Municipal Corporation - Water",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("UIT Bhiwadi",
                new ProvidersInfo("Customer ID", "Please enter valid Customer ID.",
                        InputType.TYPE_CLASS_NUMBER, 20, 3, -1));

        providersInfoHashMap.put("Ujjain Nagar Nigam - PHED",
                new ProvidersInfo("Business Partner No.", "Please enter valid Business Partner No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 8, -1));

        providersInfoHashMap.put("Uttarakhand Jal Sansthan",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 22, 7, -1));


        //electricity provider
        providersInfoHashMap.put("APSPDCL AP South",
                new ProvidersInfo("Service Number", "Please enter valid Service Number having minimum 9 and maximum 13 digit long(eg 1236547891)",
                        InputType.TYPE_CLASS_NUMBER, 13, 9, -1));
        providersInfoHashMap.put("Ajmer Vidyut Vitran Nigam Ltd",
                new ProvidersInfo("K Number", "Please enter your 12 digit K Number starting with 1",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, 1));
        providersInfoHashMap.put("Assam Power Distribution Company Ltd (APDCL RAPDR)",
                new ProvidersInfo("Consumer id", "Please enter your 11 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("BESCOM Bangalore",
                new ProvidersInfo("ParticularPayment ID", "Please enter valid ParticularPayment ID having fixed 10 digit length(eg 7568901235)",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("BESL Bharatpur Electricity Services Ltd",
                new ProvidersInfo("K Number ", "Please enter your 12 digit K Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));
        providersInfoHashMap.put("BEST Mumbai",
                new ProvidersInfo("Consumer Number",/*"Please enter your 10 digit Consumer Number.if you have a 9 digit Consumer Number,include comma(,) at end"*/
                        "Please enter your 12 digit K Number starting with 1",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, 1));
        providersInfoHashMap.put("BSES Rajdhani Delhi",
                new ProvidersInfo("CA Number", "Please enter your 9 digit CA Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("BSES Yamuna Delhi",
                new ProvidersInfo("CA Number", "Please enter your 9 digit CA Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("Bhagalpur Electricity",
                new ProvidersInfo("ParticularPayment Number", "Please enter your 10-15 digit ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 10, -1));
        providersInfoHashMap.put("MP pashim Kshetra -Bhopal",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("Reliance Energy",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("MP Poorv Kshetra Vidyut Vitaran - Jabalpur",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("MP Pashchim Kshetra Vidyut Vitaran - Indore",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        //new electricity provider added.............
        providersInfoHashMap.put("Adani Electricity Mumbai Limited",
                new ProvidersInfo("Consumer Number", "Please enter your 9 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));

        providersInfoHashMap.put("Assam Power Distribution Company Ltd. (Rural)",
                new ProvidersInfo("Consumer ID", "Please enter your Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Bikaner Electricity Supply Limited (BKESL)",
                new ProvidersInfo("K Number", "Please enter your K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 8, -1));  // not applicable

        providersInfoHashMap.put("CESC Kolkata",
                new ProvidersInfo("Consumer ID", "Please enter your 11 digit Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));

        providersInfoHashMap.put("CSPDCL (Chhattisgarh State Power Distribution Company Limited)", // not applicable
                new ProvidersInfo("Business Partner No.", "Please enter your Business Partner Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 8, -1));

        providersInfoHashMap.put("Chamundeshwari Electricity Supply Corp Ltd (CESCOM)",
                new ProvidersInfo("ParticularPayment ID", "Please enter your Valid ParticularPayment ID",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("DDED Daman and Diu",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 6, 1, -1));

        providersInfoHashMap.put("DNH Power",
                new ProvidersInfo("Service Connection Number", "Please enter your Valid Service Number",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("DDGVCL Dakshin Gujarat Vij",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 5, -1));

        providersInfoHashMap.put("Dakshin Haryana Bijli Vitran Nigam (DHBVN)",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 9, -1));

        providersInfoHashMap.put("Department of Power Nagaland",
                new ProvidersInfo("Consumer ID", "Please enter your Valid Consumer ID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1)); //not applicable

        providersInfoHashMap.put("Durgapur Projects Ltd.",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 5, -1));

        providersInfoHashMap.put("EPDCL Eastern Power Distribution Company of Andhra Pradesh Limited",
                new ProvidersInfo("Service Number", "Please enter your Valid Service Number",
                        InputType.TYPE_CLASS_TEXT, 20, 8, -1));

        providersInfoHashMap.put("Goa Electricity Department",
                new ProvidersInfo("Contract ParticularPayment Number", "Please enter your Valid Contract ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));

        providersInfoHashMap.put("Gulbarga Electricity Supply Company Limited",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("Himachal Pradesh State Electricity Board",
                new ProvidersInfo("K Number", "Please enter your Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("Hubli Electricity Supply Company Ltd (HESCOM)",
                new ProvidersInfo("ParticularPayment ID", "Please enter your Valid ParticularPayment ID",
                        InputType.TYPE_CLASS_NUMBER, 10, 5, -1));

        providersInfoHashMap.put("India Power",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 13, 5, -1));

        providersInfoHashMap.put("JUSCO Jamshedpur",
                new ProvidersInfo("Business Partner Number", "Please enter your Valid Business Partner Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 6, -1));

        providersInfoHashMap.put("Jaipur Vidhyut Vitaran Nigam Ltd.",
                new ProvidersInfo("K Number", "Please enter your 12 digit K Number Starting with 2",
                        InputType.TYPE_CLASS_TEXT, 12, 12, 2));

        providersInfoHashMap.put("Jharkhand Bijli Vitran Nigam Limited (JBVNL)",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 15, 3, -1));

        providersInfoHashMap.put("Jodhpur Vidhyut Vitran Nigam Ltd.",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 1, -1)); // not applicable

        providersInfoHashMap.put("KEDL Kota Electricity Distribution Ltd.",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 1, -1)); // not applicable

        providersInfoHashMap.put("KESCO Kanpur Electricity Supply Company Ltd",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 8, -1));

        providersInfoHashMap.put("MP Madhya Kshetra Vidhyut Vitran - RURAL",
                new ProvidersInfo("IVRS", "Please enter Valid IVRS",
                        InputType.TYPE_CLASS_NUMBER, 15, 7, -1));

        providersInfoHashMap.put("MSEDC Mahavitaran",
                new ProvidersInfo("Consumer Number", "Please enter Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("MGVCL Madhya Gujarat Vij",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));

        providersInfoHashMap.put("MP Poorv Kshetra - Jabalpur (Rural)",
                new ProvidersInfo("IVRS Number", "Please enter Valid IVRS Number",
                        InputType.TYPE_CLASS_NUMBER, 25, 1, -1));

        providersInfoHashMap.put("Mangalore Electricity Supply Company Ltd",
                new ProvidersInfo("ParticularPayment ID", "Please enter ParticularPayment ID",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("MEPDCL Meghalaya",
                new ProvidersInfo("Consumer ID", "Please enter Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 1, -1));

        providersInfoHashMap.put("NESCO - Odisha",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number(first 2 digit start from 32,42,52,61,62)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("NPCL Noida",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("New Delhi Municipal Council (NDMC)- Electricity",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));

        providersInfoHashMap.put("North Bihar Power Distribution Company Ltd",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 9, -1));

        providersInfoHashMap.put("PGVLC Paschim Gujarat Vij",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));

        providersInfoHashMap.put("Punjab State Power Corporation Ltd (PSPCL)",
                new ProvidersInfo("ParticularPayment Number", "Please enter Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 10, -1));

        providersInfoHashMap.put("SNDL Nagpur",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("SOUTHCO Odisha",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number(First 2 digit must start from 21,29,31,34,35,71)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Sikkim Power NON-RAPDRP",
                new ProvidersInfo("Contract Acc Number", "Please enter Valid Contract Acc Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 1, -1));

        providersInfoHashMap.put("South Bihar Power Distribution Company Ltd",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 8, -1));

        providersInfoHashMap.put("TP Ajmer Distribution Ltd",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_TEXT, 12, 12, -1));

        providersInfoHashMap.put("TSECL Tripura",
                new ProvidersInfo("Consumer ParticularPayment Number", "Please enter Valid Consumer ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 1, -1));

        providersInfoHashMap.put("Tamil Nadu Electricity Board (TNEB)",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 13, 9, -1));     // not applicable

        providersInfoHashMap.put("Tata Power - Mumbai",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("TATA POWER - DDL",
                new ProvidersInfo("Contract ParticularPayment Number", "Please enter Valid Contract ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 11, -1));

        providersInfoHashMap.put("Torrent Power",
                new ProvidersInfo("Service Number", "Please enter Valid Service Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 1, -1));

        providersInfoHashMap.put("Uttarakhand Power Corporation Limited",
                new ProvidersInfo("Service Connection Number", "Please enter Valid Service Connection Number",
                        InputType.TYPE_CLASS_TEXT, 13, 13, -1));

        providersInfoHashMap.put("UGVCL Uttar Gujarat Vij",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 5, -1));

        providersInfoHashMap.put("Uttar Haryana Bijli Vitran Nigam",
                new ProvidersInfo("ParticularPayment Number", "Please enter Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 10, -1));

        providersInfoHashMap.put("Uttar Pradesh Power Corp Ltd (UPPCL) - RURAL",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("Uttar Pradesh Power Corp Ltd (UPPCL) - URBAN",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("WESCO Odisha",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number (First 2 digit must start from 41,51,81,90,91)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("West Bengal State Electricity",
                new ProvidersInfo("Consumer ID", "Please enter Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));


        //---------------------------for hindi--------------------------------------

        //landline_provider................

        providersInfoHashMap.put("एयरटेल लैंडलाइन",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));
        providersInfoHashMap.put("बीएसएनएल लैंडलाइन",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 10, 1, -1));
        providersInfoHashMap.put("टाटा डोकोमो जीएसएम",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));
        providersInfoHashMap.put("MTNL दिल्ली",
                new ProvidersInfo("Telephone Number without STD code", "Please enter your 8 digit Telephone Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("MTNL मुंबई",
                new ProvidersInfo("Telephone Number", "Please Enter Valid 8 digit Telephone number(eg. 88778926)",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("रिलायंस",
                new ProvidersInfo("MobileNumber/Telephone Number", "Please enter valid Mobile/Landline Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 5, -1));
        providersInfoHashMap.put("टाटा डोकोमो सीडीएमए",
                new ProvidersInfo("Telephone Number", "Please enter your Telephone Number with STD code (exclude 0)",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        //Insurance Provider..............

        providersInfoHashMap.put("भारती एक्सा लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 11 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("केनरा एचएसबीसी ओबीसी लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("डीएचएफएल प्रामेरिका लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please enter your 8 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("आईसीआईसीआई प्रूडेंशियल लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please enter your 8 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 8, 8, -1));
        providersInfoHashMap.put("आईडीबीआई फेडरल लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("इंडियाफर्स्ट लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 8-16 digit/character Policy Number",
                        InputType.TYPE_CLASS_TEXT, 16, 8, -1));
        providersInfoHashMap.put("मैक्स लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 9 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("एसबीआई लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 11 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("टाटा लाइफ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Your 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));


        //No Validations found

        /*
        providersInfoHashMap.put("Reliance Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));
        providersInfoHashMap.put("Birla Sun Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));

        providersInfoHashMap.put("Kotak Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));
                        providersInfoHashMap.put("PNB MetLife Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,1,20,-1));

        providersInfoHashMap.put("Sahara Life Insurance",
                new ProvidersInfo("Policy Number","Please enter a valid Policy no.",
                        InputType.TYPE_CLASS_NUMBER,-1,-1,-1));*/


        // Car Insurance Provider Validation............
        providersInfoHashMap.put("बजाज अलियांज कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter 10 digit Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("भारती एक्सा कार इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("चोलामंडलम कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("फ्यूचर जनरली कार इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एचडीएफसी एर्गो कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("इफको टोकियो कार बीमा योजना",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("लिबर्टी कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("राष्ट्रीय बीमा कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("न्यू इंडिया एश्योरेंस कार इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ओरिएंटल कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रहेजा QBE कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रिलायंस जनरल कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रॉयल सुंदरम कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एसबीआई कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("श्रीराम कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("टाटा एआईजी कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनाइटेड इंडिया कार इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनिवर्सल सोमपो कार बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Bike Insurance Provider Validation...........
        providersInfoHashMap.put("बजाज अलियांज टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("भारती एक्सा टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एचडीएफसी एर्गो टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("इफको टोकियो टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रिलायंस टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनिवर्सल सोमपो टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रॉयल सुंदरम टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("न्यू इंडिया एश्योरेंस टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("लिबर्टी टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("नेशनल इंश्योरेंस टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ओरिएंटल इंश्योरेंस टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एसबीआई टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("श्रीराम टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("टाटा एजीआई टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनाइटेड इंडिया टू व्हीलर इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Health Insurance Provider Validation............
        providersInfoHashMap.put("बजाज अलियांज हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("भारती एक्सा हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("चोलामंडलम स्वास्थ्य बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("सिग्ना TTK हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("फ्यूचर जेनरल हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एचडीएफसी एर्गो हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("इफको टोकियो स्वास्थ्य बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("लिबर्टी हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एलआईसी जीवन आरोग्य योजना (स्वास्थ्य बीमा)",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("मैक्स बुपा स्वास्थ्य बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("नेशनल स्वास्थ्य बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("न्यू इंडिया हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("ओरिएंटल हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रहेजा QBE हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रिलायंस हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रेलिगेयर हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("रॉयल सुंदरम स्वास्थ्य बीमा",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एसबीआई हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("स्टार हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("टाटा एआईजी हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनाइटेड इंडिया हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("यूनिवर्सल सोमपो हेल्थ इंश्योरेंस",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        // Term Insurance Provider Validation......

        providersInfoHashMap.put("एगॉन लाइफ टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("अवीवा टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("बजाज अलियांज टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("भारती एक्सा टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("बिड़ला सन लाइफ टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("केनरा एचएसबीसी टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("डीएचएफएल प्राइमरिका टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एचडीएफसी लाइफ टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("आईसीआईसीआई प्रूडेंशियल टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("आईडीबीआई फेडरल टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("इंडियाफर्स्ट टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("कोटक लाइफ टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एलआईसी टर्म इंश्योरेंस प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("मैक्स लाइफ टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("पीएनबी मेटलाइफ टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("सहारा लाइफ टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("एसबीआई लाइफ टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("श्रीराम लाइफ टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));

        providersInfoHashMap.put("टाटा एआईए टर्म प्लान",
                new ProvidersInfo("Policy Number", "Please Enter Valid Policy Number",
                        InputType.TYPE_CLASS_NUMBER, 16, 8, -1));


        //electricity provider
        providersInfoHashMap.put("APSPDCL AP दक्षिण",
                new ProvidersInfo("Service Number", "Please enter valid Service Number having minimum 9 and maximum 13 digit long(eg 1236547891)",
                        InputType.TYPE_CLASS_NUMBER, 13, 9, -1));
        providersInfoHashMap.put("अजमेर विद्युत वितरण निगम लिमिटेड",
                new ProvidersInfo("K Number", "Please enter your 12 digit K Number starting with 1",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, 1));
        providersInfoHashMap.put("असम विद्युत वितरण कंपनी लिमिटेड (APDCL RAPDR)",
                new ProvidersInfo("Consumer id", "Please enter your 11 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));
        providersInfoHashMap.put("BESCOM बैंगलोर",
                new ProvidersInfo("ParticularPayment ID", "Please enter valid ParticularPayment ID having fixed 10 digit length(eg 7568901235)",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("BESL भरतपुर विद्युत सेवा लिमिटेड",
                new ProvidersInfo("K Number ", "Please enter your 12 digit K Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));
        providersInfoHashMap.put("BEST मुंबई",
                new ProvidersInfo("Consumer Number",/*"Please enter your 10 digit Consumer Number.if you have a 9 digit Consumer Number,include comma(,) at end"*/
                        "Please enter your 12 digit K Number starting with 1",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, 1));
        providersInfoHashMap.put("BSES Rajdhani Delhi",
                new ProvidersInfo("CA Number", "Please enter your 9 digit CA Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("BSES यमुना दिल्ली",
                new ProvidersInfo("CA Number", "Please enter your 9 digit CA Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));
        providersInfoHashMap.put("भागलपुर बिजली वितरण लिमिटेड",
                new ProvidersInfo("ParticularPayment Number", "Please enter your 10-15 digit ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 10, -1));
        providersInfoHashMap.put("MP पश्चिम क्षेत्र -भोपाल",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("रिलायंस एनर्जी",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("MP पूर्व क्षेत्र विद्युत वितरण - जबलपुर",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        providersInfoHashMap.put("MP पश्चिम क्षेत्र विद्युत वितरण - इंदौर",
                new ProvidersInfo("Consumer Number", "Please enter your 10 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));
        //new electricity provider added.............
        providersInfoHashMap.put("अडानी इलेक्ट्रिसिटी मुंबई लिमिटेड",
                new ProvidersInfo("Consumer Number", "Please enter your 9 digit Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));

        providersInfoHashMap.put("असम विद्युत वितरण कंपनी लिमिटेड (ग्रामीण)",
                new ProvidersInfo("Consumer ID", "Please enter your Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("बीकानेर बिजली आपूर्ति लिमिटेड (BKESL)",
                new ProvidersInfo("K Number", "Please enter your K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 8, -1));  // not applicable

        providersInfoHashMap.put("CESC कोलकाता",
                new ProvidersInfo("Consumer ID", "Please enter your 11 digit Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));

        providersInfoHashMap.put("CSPDCL (छत्तीसगढ़ राज्य विद्युत वितरण कंपनी लिमिटेड)", // not applicable
                new ProvidersInfo("Business Partner No.", "Please enter your Business Partner Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 8, -1));

        providersInfoHashMap.put("चामुंडेश्वरी विद्युत आपूर्ति कॉर्प लिमिटेड (CESCOM)",
                new ProvidersInfo("ParticularPayment ID", "Please enter your Valid ParticularPayment ID",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("डीडीएड दमन और दीव",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 6, 1, -1));

        providersInfoHashMap.put("DNH पावर",
                new ProvidersInfo("Service Connection Number", "Please enter your Valid Service Number",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("DDGVCL दक्षिण गुजरात विज",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 5, -1));

        providersInfoHashMap.put("दक्षिण हरियाणा बिज़ली विट्रान निगम (डीएचबीवीएन)",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 9, -1));

        providersInfoHashMap.put("बिजली विभाग नागालैंड",
                new ProvidersInfo("Consumer ID", "Please enter your Valid Consumer ID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1)); //not applicable

        providersInfoHashMap.put("दुर्गापुर प्रोजेक्ट्स लि.",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 5, -1));

        providersInfoHashMap.put("आंध्र प्रदेश लिमिटेड की ईपीडीसीएल ईस्टर्न पावर डिस्ट्रीब्यूशन कंपनी",
                new ProvidersInfo("Service Number", "Please enter your Valid Service Number",
                        InputType.TYPE_CLASS_TEXT, 20, 8, -1));

        providersInfoHashMap.put("गोवा बिजली विभाग",
                new ProvidersInfo("Contract ParticularPayment Number", "Please enter your Valid Contract ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 11, -1));

        providersInfoHashMap.put("गुलबर्गा इलेक्ट्रिसिटी सप्लाई कंपनी लिमिटेड",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("हिमाचल प्रदेश राज्य विद्युत बोर्ड",
                new ProvidersInfo("K Number", "Please enter your Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("हुबली विद्युत आपूर्ति कंपनी लिमिटेड (HESCOM)",
                new ProvidersInfo("ParticularPayment ID", "Please enter your Valid ParticularPayment ID",
                        InputType.TYPE_CLASS_NUMBER, 10, 5, -1));

        providersInfoHashMap.put("इंडिया पावर",
                new ProvidersInfo("Consumer Number", "Please enter your Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 13, 5, -1));

        providersInfoHashMap.put("JUSCO जमशेदपुर",
                new ProvidersInfo("Business Partner Number", "Please enter your Valid Business Partner Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 6, -1));

        providersInfoHashMap.put("जयपुर विधुत निगम लिमिटेड",
                new ProvidersInfo("K Number", "Please enter your 12 digit K Number Starting with 2",
                        InputType.TYPE_CLASS_TEXT, 12, 12, 2));

        providersInfoHashMap.put("झारखंड बीजली वितरण निगम लिमिटेड (JBVNL)",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 15, 3, -1));

        providersInfoHashMap.put("जोधपुर विधुत निगम लिमिटेड",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 1, -1)); // not applicable

        providersInfoHashMap.put("KEDL कोटा विद्युत वितरण लिमिटेड",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_NUMBER, 20, 1, -1)); // not applicable

        providersInfoHashMap.put("KESCO कानपुर विद्युत आपूर्ति कंपनी लिमिटेड",
                new ProvidersInfo("ParticularPayment Number", "Please enter your Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 8, -1));

        providersInfoHashMap.put("म.प्र. मध्यक्षेत्र विधुत वितरण - ग्रामीण",
                new ProvidersInfo("IVRS", "Please enter Valid IVRS",
                        InputType.TYPE_CLASS_NUMBER, 15, 7, -1));

        providersInfoHashMap.put("MSEDC महावितरण",
                new ProvidersInfo("Consumer Number", "Please enter Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("MGVCL मध्य गुजरात विज",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));

        providersInfoHashMap.put("म.प्र. पूरव क्षेत्र - जबलपुर (ग्रामीण)",
                new ProvidersInfo("IVRS Number", "Please enter Valid IVRS Number",
                        InputType.TYPE_CLASS_NUMBER, 25, 1, -1));

        providersInfoHashMap.put("मैंगलोर विद्युत आपूर्ति कंपनी लिमिटेड",
                new ProvidersInfo("ParticularPayment ID", "Please enter ParticularPayment ID",
                        InputType.TYPE_CLASS_TEXT, 10, 10, -1));

        providersInfoHashMap.put("MEPDCL मेघालय",
                new ProvidersInfo("Consumer ID", "Please enter Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 1, -1));

        providersInfoHashMap.put("NESCO - ओडिशा",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number(first 2 digit start from 32,42,52,61,62)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("एनपीसीएल नोएडा",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("नई दिल्ली नगरपालिका परिषद (NDMC) - बिजली",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));

        providersInfoHashMap.put("उत्तर बिहार विद्युत वितरण कंपनी लि.",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 9, -1));

        providersInfoHashMap.put("PGVLC पश्चिम गुजरात विज",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 5, -1));

        providersInfoHashMap.put("पंजाब स्टेट पावर कॉर्पोरेशन लिमिटेड (PSPCL)",
                new ProvidersInfo("ParticularPayment Number", "Please enter Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 10, -1));

        providersInfoHashMap.put("एसएनडीएल नागपुर",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("SOUTHCO ओडिशा",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number(First 2 digit must start from 21,29,31,34,35,71)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("सिक्किम पावर एनओएन-आरएपीडीआरपी",
                new ProvidersInfo("Contract Acc Number", "Please enter Valid Contract Acc Number",
                        InputType.TYPE_CLASS_NUMBER, 9, 1, -1));

        providersInfoHashMap.put("साउथ बिहार पावर डिस्ट्रीब्यूशन कंपनी लि",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 8, -1));

        providersInfoHashMap.put("टीपी अजमेर डिस्ट्रीब्यूशन लि",
                new ProvidersInfo("K Number", "Please enter Valid K Number",
                        InputType.TYPE_CLASS_TEXT, 12, 12, -1));

        providersInfoHashMap.put("TSECL त्रिपुरा",
                new ProvidersInfo("Consumer ParticularPayment Number", "Please enter Valid Consumer ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 1, -1));

        providersInfoHashMap.put("तमिलनाडु बिजली बोर्ड (TNEB)",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 13, 9, -1));     // not applicable

        providersInfoHashMap.put("टाटा पावर - मुंबई",
                new ProvidersInfo("CA Number", "Please enter Valid CA Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("टाटा पावर - डीडीएल",
                new ProvidersInfo("Contract ParticularPayment Number", "Please enter Valid Contract ParticularPayment Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 11, -1));

        providersInfoHashMap.put("टोरेंट पावर",
                new ProvidersInfo("Service Number", "Please enter Valid Service Number",
                        InputType.TYPE_CLASS_NUMBER, 15, 1, -1));

        providersInfoHashMap.put("उत्तराखंड पावर कॉर्पोरेशन लिमिटेड",
                new ProvidersInfo("Service Connection Number", "Please enter Valid Service Connection Number",
                        InputType.TYPE_CLASS_TEXT, 13, 13, -1));

        providersInfoHashMap.put("UGVCL उत्तर गुजरात विज",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 11, 5, -1));

        providersInfoHashMap.put("उत्तर हरियाणा बिजली वितरण निगम",
                new ProvidersInfo("ParticularPayment Number", "Please enter Valid ParticularPayment Number",
                        InputType.TYPE_CLASS_TEXT, 12, 10, -1));

        providersInfoHashMap.put("उत्तर प्रदेश पावर कॉर्प लिमिटेड (UPPCL) - ग्रामीण",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("उत्तर प्रदेश पावर कॉर्प लिमिटेड (UPPCL) - URBAN",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number",
                        InputType.TYPE_CLASS_NUMBER, 12, 10, -1));

        providersInfoHashMap.put("WESCO ओडिशा",
                new ProvidersInfo("Consumer Number", "Please enter Valid Consumer Number (First 2 digit must start from 41,51,81,90,91)",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("पश्चिम बंगाल राज्य बिजली",
                new ProvidersInfo("Consumer ID", "Please enter Valid Consumer ID",
                        InputType.TYPE_CLASS_NUMBER, 9, 9, -1));


        //Gas Provider
        providersInfoHashMap.put("अदानी गैस",
                new ProvidersInfo("Customer ID", "Please enter your 10 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("अवंतिका गैस लिमिटेड",
                new ProvidersInfo("Customer ID", "Please enter your 10-16 digit Customer ID",
                        InputType.TYPE_CLASS_TEXT, 16, 10, -1));

        providersInfoHashMap.put("सेंट्रल यूपी गैस",
                new ProvidersInfo("Customer ID", "Please enter your 1-20 digit Customer ID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("चारित्र गैस सहकारी मंडली लिमिटेड",
                new ProvidersInfo("Customer ID", "Please enter your 1-5 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 5, 1, -1));

        providersInfoHashMap.put("गेल गैस लिमिटेड",
                new ProvidersInfo("Valid BP No.", "Please enter your 10 digit numeric BP no",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("गुजरात गैस",
                new ProvidersInfo("Customer ID", "Please enter valid 9-12 digit Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 9, -1));

        providersInfoHashMap.put("हरियाणा सिटी गैस - कपिल चोपड़ा एंटरप्राइज",
                new ProvidersInfo("CRN Number", "Please enter 8-12 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("इंडियन ऑयल अडानी गैस प्राइवेट लिमिटेड",
                new ProvidersInfo("Customer ID", "Please enter fixed 10 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));


        providersInfoHashMap.put("इंद्रप्रस्थ गैस",
                new ProvidersInfo("BP Number", "Please enter your 10 digit numeric BP number",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("महानगर गैस",
                new ProvidersInfo("CA Number", "Please enter your 12 digit numeric CA number",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("महारास्ट्र नेचुरल गैस लिमिटेड",
                new ProvidersInfo("BP Number", "Please enter 7-10 digit numeric CRN number",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));


        providersInfoHashMap.put("साबरमती गैस लिमिटेड(SGL)",
                new ProvidersInfo("Custom ID", "Please enter your 12 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("सिटी एनर्जी",
                new ProvidersInfo("ARN Number", "Please enter your 7-9 digit numeric Customer ID",
                        InputType.TYPE_CLASS_NUMBER, 9, 7, -1));

        providersInfoHashMap.put("त्रिपुरा नेचुरल गैस कंपनी लि",
                new ProvidersInfo("CONSUMER NO.", "Please enter valid Consumer Number",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("यूनीक सेंट्रल पाइप्ड गैस्स प्राइवेट लिमिटेड (UCPGPL)",
                new ProvidersInfo("Consumer Number", "Please enter 8 digit CRN number",
                        InputType.TYPE_CLASS_TEXT, 8, 8, -1));

        providersInfoHashMap.put("वडोदरा गैस लिमिटेड",
                new ProvidersInfo("Consumer Number", "Please enter 7 digit numeric Consumer number",
                        InputType.TYPE_CLASS_NUMBER, 7, 7, -1));


        // Water provider..........
        providersInfoHashMap.put("बैंगलोर जल आपूर्ति और सीवरेज बोर्ड",
                new ProvidersInfo("RR No.", "Please enter valid RR No.",
                        InputType.TYPE_CLASS_TEXT, 8, 8, -1));

        providersInfoHashMap.put("भोपाल नगर निगम - पानी",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 1, -1));


        providersInfoHashMap.put("दिल्ली जल बोर्ड",
                new ProvidersInfo("K No.", "Please enter valid K No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 10, -1));

        providersInfoHashMap.put("ग्रेटर वारंगल नगर निगम - जल",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 7, -1));

        providersInfoHashMap.put("ग्वालियर नगर निगम - जल",
                new ProvidersInfo("Connection ID", "Please enter valid Connection ID",
                        InputType.TYPE_CLASS_TEXT, 15, 1, -1));

        providersInfoHashMap.put("हरियाणा शहरी विकास प्राधिकरण - जल विधेयक",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 20, 2, -1));

        providersInfoHashMap.put("हैदराबाद मेट्रोपॉलिटन वाटर सप्लाई एंड सीरल बोर्ड",
                new ProvidersInfo("CAN No.", "Please enter valid CAN No.",
                        InputType.TYPE_CLASS_TEXT, 25, 2, -1));

        providersInfoHashMap.put("इंदौर नगर निगम - पानी",
                new ProvidersInfo("Service No", "Please enter valid Service No.",
                        InputType.TYPE_CLASS_NUMBER, 16, 6, -1));

        providersInfoHashMap.put("जबलपुर नगर निगम - पानी",
                new ProvidersInfo("Service No.", "Please enter valid Service No.",
                        InputType.TYPE_CLASS_NUMBER, 16, 6, -1));

        providersInfoHashMap.put("लुधियाना नगर निगम",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 1, -1));

        providersInfoHashMap.put("नगर निगम जालंधर",
                new ProvidersInfo("UID", "Please enter valid UID",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("गुरुग्राम नगर निगम",
                new ProvidersInfo("K No.", "Please enter valid K No.",
                        InputType.TYPE_CLASS_TEXT, 20, 7, -1));

        providersInfoHashMap.put("नई दिल्ली नगरपालिका परिषद (NDMC) - जल",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 7, -1));

        providersInfoHashMap.put("PHED - राजस्थान",
                new ProvidersInfo("CID Code", "Please enter valid CID Code",
                        InputType.TYPE_CLASS_NUMBER, 12, 12, -1));

        providersInfoHashMap.put("पुणे नगर निगम - जल",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_TEXT, 20, 1, -1));

        providersInfoHashMap.put("यूआईटी भिवाड़ी",
                new ProvidersInfo("Customer ID", "Please enter valid Customer ID.",
                        InputType.TYPE_CLASS_NUMBER, 20, 3, -1));

        providersInfoHashMap.put("उज्जैन नगर निगम - PHED",
                new ProvidersInfo("Business Partner No.", "Please enter valid Business Partner No.",
                        InputType.TYPE_CLASS_NUMBER, 10, 8, -1));

        providersInfoHashMap.put("उत्तराखंड जल संस्थान",
                new ProvidersInfo("Consumer No.", "Please enter valid Consumer No.",
                        InputType.TYPE_CLASS_NUMBER, 22, 7, -1));

    }

    public static ProviderStatic getInstance() {
        return mProviderSatic;
    }


}
