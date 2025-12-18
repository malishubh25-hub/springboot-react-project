package com.baji.common.utils;


import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OmsUtils {


	 private static String input;
		private static int num;
		
		private static String[] units ={ "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine" };
		
		private static String[] teen = { " Ten", " Eleven", " Twelve", " Thirteen"," Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen"," Nineteen" };
		
		private static String[] tens = { " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
		
		private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh", " Crore" };
	
	public static boolean isNotEmpty(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String) {
			String text = (String) value;
			return !text.trim().isEmpty() && !"null".equalsIgnoreCase(text.trim());
		}
		if (value instanceof Collection) {
			return !((Collection<?>) value).isEmpty();
		}
		if (value instanceof Map) {
			return !((Map<?, ?>) value).isEmpty();
		}
		if (value instanceof Object[]) {
			return ((Object[]) value).length > 0;
		}
		if (value instanceof StringBuffer || value instanceof StringBuilder) {
			String text = value.toString();
			return !text.trim().isEmpty() && !"null".equalsIgnoreCase(text.trim());
		}
		return true;
	}
	
	public static String concatenateNonNullStrings(String... strings) {
       return Arrays.stream(strings)
               .filter(Objects::nonNull)
               .collect(Collectors.joining(" "));
   }


	public static boolean isEmpty(Object value) {
		return !isNotEmpty(value);
	}

	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	public static String randomString() {
		return RandomStringUtils.random(8);
	}

	public static String randomAlphabetic() {
		return RandomStringUtils.randomAlphabetic(1);
	}

	public static String randomValue() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public static String randomNumericValue(int digitCount) {
		return RandomStringUtils.randomNumeric(digitCount);
	}

	public static String generateAlphaNumericPassword() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	public static boolean isVendor(String adminType) {
		boolean isVendor = false;
		if (OmsUtils.isNotEmpty(adminType)) {
			if (!OmsUtils.isStrEqualS(Constants.ADMIN_TYPE, adminType)
					&& !OmsUtils.isStrEqualS(Constants.SUPERADMIN_TYPE, adminType)
					&& !OmsUtils.isStrEqualS(Constants.OCTAADMIN_TYPE, adminType)) {
				isVendor = true;
			}
		}
		return isVendor;
	}

	public static boolean isStore(String adminType){
		if(isNotEmpty(adminType) && isStrEqualS(adminType, Constants.STORE_TYPE)){
			return true;
		}
		return false;
	}

	public static boolean isStrEqualS(String first, String second) {
		first = first != null ? first : "";
		second = second != null ? second : "";

		if (first.equalsIgnoreCase(second)) {
			return true;
		} else {
			return false;
		}
	}

	public static void processCodes(String codes, List<String> codeList) {
		try {
			String[] codeSplit = codes.split(",");
			for (int i = 0; i < codeSplit.length; i++) {
				if (isNotEmpty(codeSplit[i])) {
					codeList.add(codeSplit[i].trim());
				}
			}
		} catch (Exception e) {
//			log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//					+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
		}
	}

	public static void invokeSetProperty(Object obj, String prop, Object paramValue) throws Exception {
		String upperProp = prop.isEmpty() ? "" : Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
		java.lang.reflect.Method method = obj.getClass().getMethod("set" + upperProp, paramValue.getClass());
		if (method != null)
			method.invoke(obj, paramValue);
		else
			throw new NoSuchMethodException("No such method: set" + upperProp);
	}

	public static String invokeGetProperty(Object obj, String prop) throws Exception {
		String mname = "get" + prop;
		Class<?>[] types = new Class[] {};
		java.lang.reflect.Method method = obj.getClass().getMethod(mname, types);
		Object result = method.invoke(obj, new Object[0]);
		String value = (String) result;
		return value;
	}

	public static String printBulkUploadTimeEstimation(long startTIme, long endTime, int countDone, int totalCount) {
		String estMsg = "";
		try {
			long processingtime = endTime - startTIme;
			long singleProcessTime = processingtime / countDone;
			long totalEstimatedSec = (singleProcessTime / 1000) * (long) (totalCount - countDone);
			long totalEstimatedMin = 0L, totalEstimatedHr = 0L;

			if (totalEstimatedSec > 60) {
				totalEstimatedMin = totalEstimatedSec / 60;
				totalEstimatedSec = totalEstimatedSec % 60;
			}
			if (totalEstimatedMin > 60) {
				totalEstimatedHr = totalEstimatedMin / 60;
				totalEstimatedMin = totalEstimatedMin % 60;
			}
			estMsg = " Batch Job Estimated Completion time : HH:MM:SS -> "
					+ (totalEstimatedHr < 10 ? "0" + totalEstimatedHr : totalEstimatedHr) + ":"
					+ (totalEstimatedMin < 10 ? "0" + totalEstimatedMin : totalEstimatedMin) + ":" + totalEstimatedSec;
		} catch (Exception e) {
//			log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//					+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
		}
		return estMsg;
	}

	public static boolean isValidDate(String date) {
		try {
			LocalDate.parse(date);
		} catch (Exception e) {
			System.out.println("Invalid date: " + date);
			return false;
		}
		return true;
	}

	public static boolean isListEmpty(List list) {
		return isEmpty(list);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	public static boolean isFileExist(String filePath) {
		boolean isExist = false;
		try {
			File file = new File(filePath);
			if (file.exists() && !file.isDirectory()) {
				isExist = true;
			}
		} catch (Exception e) {
//			log.info("Cause " + e.getCause() + " Message " + e.getMessage() + " Method Name "
//					+ e.getStackTrace()[0].getMethodName() + " Line Number" + e.getStackTrace()[0].getLineNumber());
		}
		return isExist;
	}

	public static boolean isDecimalPoint(String str) {
		return str.matches("^\\d+\\.\\d+"); // match a number with decimal point
	}

	public static boolean isDecimal(String str) {
		return StringUtils.isNumeric(str);
	}

	public static boolean isDigitOnly(String str) {
		return str.matches("\\d+"); // match a number with only Digits.
	}

	public static boolean isMapEmpty(Map map) {
		if (map == null || map.isEmpty() || map.size() == 0 ){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isKeyEmpty(Map map,String key) {
		if (isMapEmpty(map)  || isEmpty(key)){
			return true;
		}else if(!isEmpty(key) && !isMapEmpty(map) &&  (!map.containsKey(key) || isEmpty(map.get(key)))){
			return true;
		}
		else{
			return false;
		}
	}

	public static String getSingleQoatedString(String value){
		StringBuilder sb = new StringBuilder("");
		if(OmsUtils.isNotEmpty(value)){
			String strArr[] = value.split(",");
			for(String n:strArr){
				if(sb.length() > 0) sb.append(',');
				sb.append("'").append(n).append("'");
			}
		}
		return sb.toString();
	}

	public static String getAddressDetails(String a1,String a2, String a3, String ct, String st, String zip){
		String dtl = Constants.BLANK;
		dtl = dtl.trim() + (isNotEmpty(a1) ? " "+a1 : "");
		dtl = dtl.trim() + (isNotEmpty(a2) ? " "+a2 : "");
		dtl = dtl.trim() + (isNotEmpty(a2) ? "\n "+a3 : "");
		dtl = dtl.trim() + (isNotEmpty(ct) ? "\n "+ct : "");
		dtl = dtl.trim() + (isNotEmpty(st) ? " "+st : "");
		dtl = dtl.trim() + (isNotEmpty(zip) ? " - "+zip : "");

		return dtl;
	}

	public static String prepareName(String fn,String mn, String ln){
		String nm="";
		if(isNotEmpty(fn)) nm = nm.trim() + fn.toUpperCase();
		if(isNotEmpty(mn)) nm = nm.trim() +" "+ mn.toUpperCase();
		if(isNotEmpty(ln)) nm = nm.trim() +" "+ ln.toUpperCase();
		if(nm.length()>0) nm = nm.replace("  ", " ").trim();
		return nm;
	}

	public static boolean isObjectEmpty(Object param) {
		if (param == null){
			return true;
		}else{
			return false;	
		}
	}

	public static double roundOff(BigDecimal amount) {
		double val=0;
		try {
			int n=amount.intValue();
			if(n%10==5) {
				val=n+5;
			}else if(n%10>5 || n%10==0) {
				val=(n + 4) / 5 * 5;
			}else {
				val=(n - 1) / 5 * 5;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(e.getMessage());
		}
		return val;
	}
		

	public static String trimString(String variableName) {
		byte[] arr = variableName.getBytes();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == -96) {
				arr[i] = ' ';
			}
		}
		variableName = new String(arr);
		return variableName.trim();
	}

	// Helper method to set a property if the value is not null
	public static <T> void setIfNotNull(Consumer<T> setter, T value) {
		if (Objects.nonNull(value)) {
			setter.accept(value);
		}
	}

	public static <T> void setIfNotNull(Consumer<T> setter, T value, T defaultValue) {
		if (Objects.nonNull(value)) {
			setter.accept(value);
		} else {
			setter.accept(defaultValue);
		}
	}
	
	public static String trimToNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
	public static String normalizeEmail(String email) {
        String e = trimToNull(email);
        return (e == null) ? null : e.toLowerCase();
    }
	
	public static String convertNumberToWords(int n) {
		input = numToString(n);

		String converted = "";

		int pos = 1;
		boolean hun = false;
		while (input.length() > 0) {
			if (pos == 1){ // TENS AND UNIT POSITION				
				if (input.length() >= 2){ // TWO DIGIT NUMBERS					
					String temp = input.substring(input.length() - 2,input.length());
					input = input.substring(0, input.length() - 2);
					converted += digits(temp);
				} else if (input.length() == 1){ // 1 DIGIT NUMBER					
					converted += digits(input);
					input = "";
				}
				pos++;
			} else if (pos == 2) { // HUNDRED POSITION				

				String temp = input.substring(input.length() - 1,
						input.length());
				input = input.substring(0, input.length() - 1);
				if (converted.length() > 0 && digits(temp) != "") {
					converted = (digits(temp) + maxs[pos] + " and") + converted;
					hun = true;
				} else {
					if (digits(temp) == "")
						;
					else
						converted = (digits(temp) + maxs[pos]) + converted;
					hun = true;
				}
				pos++;
			} else if (pos > 2){ // REMAINING NUMBERS PAIRED BY TWO
			
				if (input.length() >= 2){ // EXTRACT 2 DIGITS

					String temp = input.substring(input.length() - 2, input.length());
					input = input.substring(0, input.length() - 2);
					if (!hun && converted.length() > 0)
						converted = digits(temp) + maxs[pos] + " and"
								+ converted;
					else {
						if (digits(temp) == "")
							;
						else
							converted = digits(temp) + maxs[pos] + converted;
					}
				} else if (input.length() == 1){// EXTRACT 1 DIGIT					

					if (!hun && converted.length() > 0)
						converted = digits(input) + maxs[pos] + " and"
								+ converted;
					else {
						if (digits(input) == "")
							;
						else
							converted = digits(input) + maxs[pos] + converted;
						input = "";
					}
				}
				pos++;
			}
		}
		/*return converted + " Only/-";*/
		return converted ;
	}
	
	private static String digits(String temp){ // TO RETURN SELECTED NUMBERS IN WORDS	    
       String converted="";
       
       for(int i=temp.length()-1;i >= 0;i--){
          int ch=temp.charAt(i)-48;
           
          if(i==0	&&	ch>1 && temp.length()> 1)
           converted=tens[ch-2]+converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
           
          else if(i==0&&ch==1&&temp.length()==2) {// IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS	            
               int sum=0;
               for(int j=0;j < 2;j++)
               sum=(sum*10)+(temp.charAt(j)-48);
               return teen[sum-10];
           }
           else{
               if(ch > 0)
               converted = units[ch] + converted;
           } // IF SINGLE DIGIT PROVIDED
       }
       return converted;
   }
	
	 private static String numToString(int x){ // CONVERT THE NUMBER TO STRING	    	
	        String num="";
	        while(x!=0) {
	            num=((char)((x%10)+48))+num;
	            x/=10;
	        }
	       return num;
	    }
	 
	 public static Integer str2Int(String str) {
			Integer result = null;
			if (null == str || 0 == str.length()) {
				return null;
			}
			try {
				result = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				String negativeMode = "";
				if (str.indexOf('-') != -1)
					negativeMode = "-";
				str = str.replaceAll("-", "");
				if (str.indexOf('.') != -1) {
					str = str.substring(0, str.indexOf('.'));
					if (str.length() == 0) {
						return (Integer) 0;
					}
				}
				String strNum = str.replaceAll("[^\\d]", "");
				if (0 == strNum.length()) {
					return null;
				}
				result = Integer.parseInt(negativeMode + strNum);
			}
			return result;
		}
	

}
