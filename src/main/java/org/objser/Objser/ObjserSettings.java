package org.objser.Objser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ObjserSettings {
	
	private List<FieldExclusionStrategy> fieldExclusionStrategies;
	private List<ObjserLeaf> leafs;
	private boolean formattedPrint;
	
	
	public ObjserSettings() {
		this.fieldExclusionStrategies = new ArrayList<>();
		this.leafs = new ObjserDefaultLeafs().getLeafs();
		this.formattedPrint = true;
	}
	
	public ObjserSettings addFieldExclusionStrategy(FieldExclusionStrategy... fieldExclusionStrategies) {
		return addFieldExclusionStrategy(Arrays.asList(fieldExclusionStrategies));
	}
	
	public ObjserSettings addFieldExclusionStrategy(List<FieldExclusionStrategy> fieldExclusionStrategies) {
		this.fieldExclusionStrategies.addAll(fieldExclusionStrategies);
		return this;
	}
	
	public ObjserSettings addLeafs(ObjserLeaf... leafs) {
		return addLeafs(Arrays.asList(leafs));
	}
	
	public ObjserSettings addLeafs(List<ObjserLeaf> leafs) {
		this.leafs.addAll(leafs);
		return this;
	}
	
	public ObjserSettings formattedPrint(boolean formattedPrint) {
		this.formattedPrint = formattedPrint;
		return this;
	}
	
	protected List<FieldExclusionStrategy> getFieldExclusionStrategies(){
		return fieldExclusionStrategies;
	}
	
	protected Optional<ObjserLeaf> getLeaf(Class clazz) {
		return leafs.stream().filter(item -> item.isLeaf(clazz)).findFirst();
	}

	protected Optional<ObjserLeaf> getLeaf(String className) {
		return leafs.stream().filter(item -> item.getLeafTypeName().equals(className)).findFirst();
	}

	protected boolean isLeaf(Class clazz) {
		return getLeaf(clazz).isPresent();
	}
	
	protected boolean isFormattedPrint() {
		return formattedPrint;
	}

	protected String getLeafTypeName(Class clazz) {
		Optional<ObjserLeaf> leaf = getLeaf(clazz);
		if (leaf.isPresent()) {
			return leaf.get().getLeafTypeName();
		} else {
			return "";
		}
	}

	protected Object getDefaultValue(Class clazz) {
		Optional<ObjserLeaf> leaf = getLeaf(clazz);
		if (leaf.isPresent()) {
			return leaf.get().getDefaultValue();
		} else {
			return null;
		}
	}
}
