package com.feth.play.plugin.jsonminify;

import java.io.StringWriter;

public class IndentationWriter extends StringWriter {

	/**
	 * The indentation level we are currently in
	 */
	int level = 0;
	
	/**
	 * The newline character
	 */
	final static char NEWLINE = '\n';

	@Override
	public void write(int c) {
		switch (c) {
		case '{':
		case '[':
			level++;
		case ',':
			super.write(c);
			super.write(NEWLINE);
			indent();
			break;
		case '}':
		case ']':
			super.write(NEWLINE);
			level--;
			indent();
		default:
			super.write(c);
			break;
		}
	}

	private void indent() {
		if(level > 0) {
			super.write(String.format("%" + (level * 4) + "s", ""));
		}
	}
}
