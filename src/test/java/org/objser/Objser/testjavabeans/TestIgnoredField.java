package org.objser.Objser.testjavabeans;

public class TestIgnoredField implements IBean{
	private final String shouldBeIgnored;
	private final String shouldNotBeIgnored;
	
	public TestIgnoredField(String shouldBeIgnored, String shouldNotBeIgnored) {
		this.shouldBeIgnored = shouldBeIgnored;
		this.shouldNotBeIgnored = shouldNotBeIgnored;
	}

	public String getShouldBeIgnored() {
		return shouldBeIgnored;
	}

	public String getShouldNotBeIgnored() {
		return shouldNotBeIgnored;
	}
}
