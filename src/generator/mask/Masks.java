package generator.mask;

public final class Masks {
	public static final Mask CLASSIC_EDGE = (new Mask(32)).addRect(1, 1, 14, 14).addRect(17, 1, 14, 14)
														.addRect(1, 17, 14, 14).addRect(17, 17, 14, 14)
														.addRect(0, 16, 17, 1).addRect(0, 31, 17, 1)
														.addRect(16, 0, 1, 17).addRect(31, 0, 1, 17)
														.addRect(31, 31, 1, 1);
}
