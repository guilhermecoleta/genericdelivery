package br.com.genericdelivery.view;

public enum Messages {
	M1("message1"), M2("message2"), M3("message3"), M4("message4"), M5("message5"), M6("message6"), M7("message7"), M8("message8"), M9("message9"), M10(
			"message10"), M11("message11"), M12("message12"), M13("message13"), M14("message14"), M15("message15"), M16("message16"), M17("message17"), M18(
			"message18");

	private String tag;

	private Messages(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

}
