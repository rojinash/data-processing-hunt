import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

public class DataProcessingHunt {
	
	public static void main(String[] args) throws IOException {
		
		Stream<String> names = Files.lines(Paths.get("resources", "namesCA.txt"));
		Stream<String> sugar = Files.lines(Paths.get("resources", "sugar.txt"));
		Stream<String> height = Files.lines(Paths.get("resources", "height.txt"));
		
		//First Data Set
		int cerealCount = sugar.map(text -> Double.valueOf(text))
				.filter(value -> value > 40)
				.reduce(0, (acc, e) -> acc + 1, (accl, accr) -> accl + accr);
		System.out.println("1. How many entries have sugar content greater than 40?");
		System.out.printf("> ");
		System.out.println(cerealCount);
		System.out.println("(Source: Rdatasets data on the percentage of sugar"
				+ " in breakfast cereal) \n");
		
		
		sugar = Files.lines(Paths.get("resources", "sugar.txt"));
		Double cerealMax = sugar.map(text -> Double.parseDouble(text))
				.filter(line -> line > 40)
				.reduce(0.0, (acc, e) -> Math.max(acc, e));
			
		System.out.println("2. What is the maximum sugar content?");
		System.out.printf("> ");
		System.out.println(cerealMax);
		System.out.println("(Source: Rdatasets data on the percentage of sugar"
				+ " in breakfast cereal)\n");
	
		sugar = Files.lines(Paths.get("resources", "sugar.txt"));
		
		long repetitions = sugar.filter(text -> text.equals("38.3"))
				.count();
		
		System.out.println("3. How many times is the quantity 38.3 repeated?");
		System.out.printf("> ");
		System.out.println(repetitions);
		System.out.println("(Source: Rdatasets data on the percentage of sugar"
				+ " in breakfast cereal)\n");
		
		sugar.close();
		
		//Second Data Set
		int nameCount = names.map(text -> text.split(","))
				.filter(line -> line[3].startsWith("W"))
				.map(line -> Integer.parseInt(line[4]))
				.reduce(0, (acc, e) -> acc + e);
		
		System.out.println("4. How many baby names begin with the letter 'W'?");
		System.out.printf("> ");
		System.out.println(nameCount);
		System.out.println("(Source: Data.gov data on names by state)\n");
		names.close();
		
		names = Files.lines(Paths.get("resources", "namesCA.txt"));		
		int nameNum = names.map(text -> text.split(","))
				.filter(line -> line[2].startsWith("1970"))
				.filter(line -> line[3].contains("w"))
				.map(line -> Integer.parseInt(line[4]))
				.reduce(0, (acc, e) -> acc + e);
		
		System.out.println("5. How many baby names from 1970 contain the letter 'w' "
				+ "but don't begin with it?");
		System.out.printf("> ");
		System.out.println(nameNum);
		System.out.println("(Source: Data.gov data on names by state)\n");
		names.close();
		sugar.close();
		height.close();
		
		names = Files.lines(Paths.get("resources", "namesCA.txt"));
		int maleNum = names.map(text -> text.split(","))
				.filter(line -> line[1].startsWith("M"))
				.map(line -> Integer.parseInt(line[4]))
				.reduce(0, (acc, e) -> acc + e);
		System.out.println("6. How many male names are there?");
		System.out.printf("> ");
		System.out.println(maleNum);
		System.out.println("(Source: Data.gov data on names by state)\n");
		
		//Third Data Set
		height = Files.lines(Paths.get("resources", "height.txt"));
		int femaleShort = height.map(text -> text.split(","))
				.filter(line -> line[0].startsWith("F"))
				.map(line -> Integer.parseInt(line[2]))
				.filter(size -> size < 166)
				.reduce(0, (acc, e) -> acc + 1,(accl, accr) -> accl + accr);
		System.out.println("7. How many females are shorter than 166cm?");
		System.out.printf("> ");
		System.out.println(femaleShort);
		System.out.println("(Source: Rdatasets on self-reports on height and weight)\n");
		
		
		height = Files.lines(Paths.get("resources", "height.txt"));
		int maleMin = height.map(text -> text.split(","))
				.filter(line -> line[0].startsWith("M"))
				.map(line -> Integer.parseInt(line[1]))
				.reduce(1000, (acc, e) -> Math.min(acc, e));
				
		System.out.println("8. What is the minimum male weight in kilograms?");
		System.out.printf("> ");
		System.out.println(maleMin);
		System.out.println("(Source: Rdatasets on self-reports on height and weight)\n");
		
		height = Files.lines(Paths.get("resources", "height.txt"));
		int number = height.map(text -> text.split(","))
				.filter(line -> line[1].equals("53"))
				.reduce(0, (acc, e) -> acc + 1, (accl, accr) -> accl + accr);
				
		System.out.println("9. How many people weigh exactly 53kg?");
		System.out.printf("> ");
		System.out.println(number);
		System.out.println("(Source: Rdatasets on self-reports on height and weight)\n");
		
	}
}
