package com.hhnz.util.enmus;

public enum FileType {
	/**
     * JEPG.
     */
    JPG("FFD8FF",1,"1"),
     
    /**
     * PNG.
     */
    PNG("89504E47",2,"1"),
     
    /**
     * GIF.
     */
    GIF("47494638",3,"1"),
     
    /**
     * TIFF.
     */
    TIF("49492A00",4,"1"),
     
    /**
     * Windows Bitmap.
     */
    BMP("424D",5,"1"),
     
    /**
     * CAD.
     */
    DWG("41433130",6,"2"),
     
    /**
     * Adobe Photoshop.
     */
    PSD("38425053",7,"2"),
     
    /**
     * Rich Text Format.
     */
    RTF("7B5C727466",8,"2"),
     
    /**
     * XML.
     */
    XML("3C3F786D6C",9,"2"),
     
    /**
     * HTML.
     */
    HTML("68746D6C3E",11,"2"),
     
    /**
     * Email [thorough only].
     */
    EML("44656C69766572792D646174653A",12,"2"),
     
    /**
     * Outlook Express.
     */
    DBX("CFAD12FEC5FD746F",13,"2"),
     
    /**
     * Outlook (pst).
     */
    PST("2142444E",14,"2"),
     
    /**
     * MS Word/Excel.
     */
    XLS_DOC("D0CF11E0",15,"2"),
     
    /**
     * MS Access.
     */
    MDB("5374616E64617264204A",16,"2"),
     
    /**
     * WordPerfect.
     */
    WPD("FF575043",17,"2"),
     
    /**
     * Postscript.
     */
    EPS("252150532D41646F6265",18,"2"),
     
    /**
     * Adobe Acrobat.
     */
    PDF("255044462D312E",19,"2"),
     
    /**
     * Quicken.
     */
    QDF("AC9EBD8F",20,"2"),
     
    /**
     * Windows Password.
     */
    PWL("E3828596",21,"2"),
     
    /**
     * ZIP Archive.
     */
    ZIP("504B0304",22,"2"),
     
    /**
     * RAR Archive.
     */
    RAR("52617221",23,"2"),
     
    /**
     * Wave.
     */
    WAV("57415645",24,"2"),
     
    /**
     * AVI.
     */
    AVI("41564920",25,"2"),
     
    /**
     * Real Audio.
     */
    RAM("2E7261FD",26,"2"),
     
    /**
     * Real Media.
     */
    RM("2E524D46",27,"2"),
     
    /**
     * MPEG (mpg).
     */
    MPG("000001BA",28,"2"),
     
    /**
     * Quicktime.
     */
    MOV("6D6F6F76",29,"2"),
     
    /**
     * Windows Media.
     */
    ASF("3026B2758E66CF11",30,"2"),
     
    /**
     * MIDI.
     */
    MID("4D546864",31,"2");
     
    private String value = "";
    private int index;
    private String type;
    
 // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private FileType(String value, int index,String type) {
        this.type = type;
        this.index = index;
        this.value= value;
    }
    
    
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	// 普通方法
    public static String getType(String value) {
        for (FileType type : FileType .values()) {
            if (type.getValue() == value) {
                return type.type;
            }
        }
        return null;
    }
}