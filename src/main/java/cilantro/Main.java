package cilantro;

import java.io.PrintStream;

import javax.lang.System;
import javax.util.List;
import javax.util.Map;

import cilantro.cli.Parser;
import cilantro.io.Console;

public class Main {
	
	protected Console console;
	protected String[] arguments;
	protected List<String> parameters;
	protected Map<String, String> options;
	
	protected Parser parser = new Parser();
	
	public Main initialize(Parser parser) {
		this.parser = parser;
		this.console = new Console(System.out, System.err, System.in).install();
		return this;
	}
	
	public Main print(String message, Object... parameters) {
		return print(console.out, message, parameters);
	}
	
	public Main print(PrintStream stream, String message, Object... parameters) {
		console.printf(stream, message, parameters);
		return this;
	}
	
	public Main println() {
		return println("");
	}
	
	public Main println(String message, Object... parameters) {
		return println(console.out, message, parameters);
	}
	
	public Main println(PrintStream stream, String message, Object... parameters) {
		console.printlnf(stream, message, parameters);
		return this;
	}
	
	public Integer execute() throws Exception {
		return this.execute(this.parser.parameters(), this.parser.options());
	}
	
	public Integer execute(List<String> parameters, Map<String, String> options) throws Exception {
		this.console.info("Please implement me!");
		return 1;
	}
	
	public static void main(String[] arguments) throws Exception {
		main(System.loadClass(System.getProperties().get("main").toString()), arguments);
	}
	
	public static void main(Class<?> clazz, String[] arguments) throws Exception {
		main(clazz, new Parser(arguments));
	}
	
	public static void main(Class<?> clazz, Parser parser) throws Exception {
		Integer result = Main.class.cast(clazz.newInstance()).initialize(parser).execute();
		if (result != null)
			System.exit(result);
	}
}
