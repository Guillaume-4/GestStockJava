package views.components;

import javax.swing.text.*;

public class NumericFilter extends DocumentFilter {
    private boolean allowDecimal;

    public NumericFilter(boolean allowDecimal) {
        this.allowDecimal = allowDecimal;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null)
            return;

        if (isValidInput(fb, string))
            super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text == null)
            return;

        if (isValidInput(fb, text))
            super.replace(fb, offset, length, text, attrs);

    }

    private boolean isValidInput(FilterBypass fb, String text) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText + text;

        if (allowDecimal)
            return newText.matches("\\d*\\.?\\d*");
        else
            return newText.matches("\\d*");
    }
}
