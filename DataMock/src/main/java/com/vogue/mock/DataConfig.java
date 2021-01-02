package com.vogue.mock;


/**
 * 配置器
 */
public class DataConfig {
    private MockConfig config;
    private byte[] byteRange = {0, 127};
    private boolean[] booleanSeed = {true, false};
    private short[] shortRange = {0, 1000};
    private int[] intRange = {0, 10000};
    private float[] floatRange = {0.0f, 10000.00f};
    private double[] doubleRange = {0.0, 10000.00};
    private int decimalScale = 2;
    private long[] longRange = {0L, 10000L};
    private String[] dateRange = {"1970-01-01", "2100-12-31"};
    private int[] timeRange = {0,24,0,60,0,60};
    private int[] sizeRange = {1, 10};

    private char[] charSeed =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
                    'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private String[] stringSeed =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F",
                    "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private String numberRegex = null;
    private String stringRegex = null;

    public DataConfig(MockConfig config) {
        this.config = config;
    }

    /**
     * 配置转路器 - 设置全局配置
     * @return MockConfig
     */
    public MockConfig globalConfig(){
        return this.config;
    }

    /**
     * 配置转路器 - 切换设置全局字段模拟配置
     * @param fieldNames 属性名称
     * @return DataConfig
     */
    public DataConfig subConfig(String... fieldNames){
        return this.config.subConfig(fieldNames);
    }

    /**
     * 配置转路器 - 设置局部配置
     * @param clazz 类
     * @param fieldName 属性名称
     * @return DataConfig
     */
    public DataConfig subConfig(Class<?> clazz, String... fieldName){
        return this.config.subConfig(clazz,fieldName);
    }

    /**
     * byteRange
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig byteRange(byte min, byte max) {
        if(Math.abs(min)>Byte.MAX_VALUE || Math.abs(max) >Byte.MAX_VALUE  || min> max){
            throw new MockException("Out Of The Range Of Byte Data.");
        }
        this.byteRange[0] = min;
        this.byteRange[1] = max;
        return this;
    }

    /**
     *
     * @param booleanSeed 的
     * @return g
     */
    public DataConfig booleanSeed(boolean... booleanSeed) {
        this.booleanSeed = booleanSeed;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig shortRange(short min, short max) {
        if(Math.abs(min)>Short.MAX_VALUE || Math.abs(max) >Short.MAX_VALUE || min> max){
            throw new MockException("Out Of The Range Of Short Data.");
        }
        this.shortRange[0] = min;
        this.shortRange[1] = max;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig intRange(int min, int max) {
        if(Math.abs(min)>Integer.MAX_VALUE || Math.abs(max) >Integer.MAX_VALUE || min> max){
            throw new MockException("Out Of The Range Of Integer Data.");
        }
        this.intRange[0] = min;
        this.intRange[1] = max;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig floatRange(float min, float max) {
        if(Math.abs(min)> Float.MAX_VALUE || Math.abs(max)> Float.MAX_VALUE || min> max){
            throw new MockException("Out Of The Range Of Float Data.");
        }
        this.floatRange[0] = min;
        this.floatRange[1] = max;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig doubleRange(double min, double max) {
        if(Math.abs(min)> Double.MAX_VALUE || Math.abs(max) >Double.MAX_VALUE || min> max){
            throw new MockException("Out Of The Range Of Double Data.");
        }
        this.doubleRange[0] = min;
        this.doubleRange[1] = max;
        return this;
    }

    /**
     *
     * @param scale d
     * @return g
     */
    public DataConfig decimalScale(int scale) {
        this.decimalScale = scale;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig longRange(long min, long max) {
        if(Math.abs(min)> Long.MAX_VALUE || Math.abs(max) >Long.MAX_VALUE || min> max){
            throw new MockException("Out Of The Range Of Long Data.");
        }
        this.longRange[0] = min;
        this.longRange[1] = max;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig dateRange(String min, String max) {
        this.dateRange[0] = min;
        this.dateRange[1] = max;
        return this;
    }

    /**
     *
     * @param minHour m
     * @param maxHour h
     * @param minMinute m
     * @param maxMinute m
     * @param minSecond s
     * @param maxSecond s
     * @return g
     */
    public DataConfig timeRange(int minHour, int maxHour, int minMinute, int maxMinute, int minSecond, int maxSecond) {
        this.timeRange[0] = minHour;
        this.timeRange[1] = maxHour;
        this.timeRange[2] = minMinute;
        this.timeRange[3] = maxMinute;
        this.timeRange[4] = minSecond;
        this.timeRange[5] = maxSecond;
        return this;
    }

    /**
     *
     * @param min min
     * @param max max
     * @return g
     */
    public DataConfig sizeRange(int min, int max) {
        if(Math.abs(min)> Integer.MAX_VALUE || Math.abs(max) >Integer.MAX_VALUE || min> max){
            throw new MockException("The Size Is Out Of The Range Of Integer Data.");
        }
        this.sizeRange[0] = min;
        this.sizeRange[1] = max;
        return this;
    }

    /**
     *
     * @param stringSeed s
     * @return g
     */
    public DataConfig stringSeed(String... stringSeed) {
        this.stringSeed = stringSeed;
        return this;
    }

    /**
     *
     * @param charSeed s
     * @return g
     */
    public DataConfig charSeed(char... charSeed) {
        this.charSeed = charSeed;
        return this;
    }

    /**
     *
     * @param numberRegex s
     * @return g
     */
    @Deprecated
    public DataConfig numberXeger(String numberRegex) {
       return numberRegex(numberRegex);
    }

    /**
     *
     * @param stringRegex s
     * @return g
     */
    @Deprecated
    public DataConfig stringXeger(String stringRegex) {
        return stringRegex(stringRegex);
    }

    /**
     *
     * @param numberRegex s
     * @return g
     */
    public DataConfig numberRegex(String numberRegex) {
        this.numberRegex = numberRegex;
        return this;
    }

    /**
     *
     * @param stringRegex s
     * @return g
     */
    public DataConfig stringRegex(String stringRegex) {
        this.stringRegex = stringRegex;
        return this;
    }

    /**
     *
     * @return g
     */
    public byte[] byteRange() {
        return this.byteRange;
    }

    /**
     *
     * @return g
     */
    public boolean[] booleanSeed() {
        return this.booleanSeed;
    }


    /**
     *
     * @return g
     */
    public short[] shortRange() {
        return this.shortRange;
    }


    /**
     *
     * @return g
     */
    public int[] intRange() {
        return this.intRange;
    }


    /**
     *
     * @return g
     */
    public float[] floatRange() {
        return this.floatRange;
    }


    /**
     *
     * @return s
     */
    public double[] doubleRange() {
        return this.doubleRange;
    }

    /**
     *
     * @return s
     */
    public int decimalScale() {
        return this.decimalScale;
    }

    /**
     *
     * @return s
     */
    public long[] longRange() {
        return this.longRange;
    }


    /**
     *
     * @return s
     */
    public String[] dateRange() {
        return  this.dateRange;
    }

    /**
     *
     * @return s
     */
    public int[] timeRange() {
        return  this.timeRange;
    }


    /**
     *
     * @return s
     */
    public int[] sizeRange() {
        return this.sizeRange;
    }


    /**
     *
     * @return s
     */
    public String[] stringSeed() {
        return this.stringSeed;
    }


    /**
     *
     * @return s
     */
    public char[] charSeed() {
        return this.charSeed;
    }

    /**
     *
     * @return s
     */
    public String numberRegex() {
       return this.numberRegex ;
    }

    /**
     *
     * @return s
     */
    public String stringRegex() {
       return this.stringRegex ;
    }

}
