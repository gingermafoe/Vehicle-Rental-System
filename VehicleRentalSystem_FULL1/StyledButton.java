import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Styled rounded button with gradient, hover and press effects.
 * Usage: new StyledButton("Register", new Color(60,130,200), Color.WHITE);
 */
public class StyledButton extends JButton {
    private Color base;
    private Color textColor;
    private boolean hover = false;
    private boolean pressed = false;
    private int arc = 28; // corner roundness
    private int paddingX = 30;
    private int paddingY = 18;

    public StyledButton(String text, Color baseColor, Color textColor) {
        super(text);
        this.base = baseColor;
        this.textColor = textColor;
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(textColor);
        setFont(getFont().deriveFont(Font.BOLD, 16f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(paddingY, paddingX, paddingY, paddingX));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
            @Override
            public void mouseExited(MouseEvent e)  { hover = false; pressed = false; repaint(); }
            @Override
            public void mousePressed(MouseEvent e) { pressed = true; repaint(); }
            @Override
            public void mouseReleased(MouseEvent e){ pressed = false; repaint(); }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width += paddingX;
        d.height += paddingY;
        return d;
    }

    @Override
    protected void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // compute colors for gradient and shadow
        Color c1 = base;
        Color c2 = base.darker();
        if (hover) {
            c1 = base.brighter();
            c2 = base;
        }
        if (pressed) {
            c1 = base.darker();
            c2 = base.darker().darker();
        }

        int w = getWidth();
        int h = getHeight();

        // drop shadow
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.18f));
        g.setColor(Color.black);
        g.fillRoundRect(4, 6, w - 8, h - 8, arc, arc);
        g.setComposite(AlphaComposite.SrcOver);

        // main rounded rect with vertical gradient
        GradientPaint gp = new GradientPaint(0, 0, c1, 0, h, c2);
        g.setPaint(gp);
        g.fillRoundRect(0, 0, w, h, arc, arc);

        // subtle highlight at top
        g.setPaint(new GradientPaint(0, 0, new Color(255,255,255,90), 0, h/2, new Color(255,255,255,10)));
        g.fillRoundRect(0, 0, w, h/2, arc, arc);

        // border
        g.setColor(new Color(255,255,255,80));
        g.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

        // draw icon & text
        Icon icon = getIcon();
        int iconOffset = 0;
        if (icon != null) {
            int iy = (h - icon.getIconHeight()) / 2;
            icon.paintIcon(this, g, 12, iy);
            iconOffset = icon.getIconWidth() + 8;
        }

        // draw text centered (considering icon offset)
        String text = getText();
        g.setFont(getFont());
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int tx = (w - textWidth) / 2 + iconOffset/2;
        int ty = (h + fm.getAscent() - fm.getDescent()) / 2;

        g.setColor(getForeground());
        g.drawString(text, tx, ty);

        g.dispose();
    }
}
