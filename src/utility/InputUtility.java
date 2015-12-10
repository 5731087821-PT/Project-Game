package utility;

import java.awt.event.KeyEvent;

public class InputUtility {

	private static int mouseX,mouseY;
	private static boolean mouseLeftDown,mouseRightDown,mouseOnScreen;
	private static boolean mouseLeftTriggered,mouseRightTriggered;
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];
	
	public static int getMouseX() {
		return mouseX;
	}
	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}
	public static int getMouseY() {
		return mouseY;
	}
	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}
	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}
	public static boolean isMouseLeftClicked() {
		return mouseLeftTriggered;
	}
	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}
	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}
	public static boolean isMouseRightClicked() {
		return mouseRightTriggered;
	}
	public static void setMouseRightDown(boolean mouseRightDown) {
		InputUtility.mouseRightDown = mouseRightDown;
	}
	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}
	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}
	public static boolean isMouseLeftTriggered() {
		return mouseLeftTriggered;
	}
	public static void setMouseLeftTriggered(boolean mouseLeftTriggered) {
		InputUtility.mouseLeftTriggered = mouseLeftTriggered;
	}
	public static boolean isMouseRightTriggered() {
		return mouseRightTriggered;
	}
	public static void setMouseRightTriggered(boolean mouseRightTriggered) {
		InputUtility.mouseRightTriggered = mouseRightTriggered;
	}
	public static boolean[] getKeyPressed() {
		return keyPressed;
	}
	public static void setKeyPressed(boolean[] keyPressed) {
		InputUtility.keyPressed = keyPressed;
	}
	public static boolean[] getKeyTriggered() {
		return keyTriggered;
	}
	public static void setKeyTriggered(boolean[] keyTriggered) {
		InputUtility.keyTriggered = keyTriggered;
	}
	
	public static boolean getKeyPressed(int key) {
		if(key>255 || key <0) return false;
		return keyPressed[key];
	}
	public static void setKeyPressed(int key,boolean pressed) {
		if(key>255 || key <0) return;
		keyPressed[key] = pressed;
	}
	
	public static boolean getKeyTriggered(int key) {
		if(key>255 || key <0) return false;
		return keyTriggered[key];
	}
	
	public static void setKeyTriggered(int key,boolean pressed) {
		if(key>255 || key <0) return;
		keyTriggered[key] = pressed;
	}
	
	public static void postUpdate(){
		mouseLeftTriggered = false;
		mouseRightTriggered = false;
		keyTriggered[KeyEvent.VK_SPACE] = false;
		for(int i=0;i<keyTriggered.length;i++){
			keyTriggered[i] = false;
		}
	}
}
