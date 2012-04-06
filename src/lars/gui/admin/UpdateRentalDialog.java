package lars.gui.admin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import lars.RentalItem;
import lars.db.RentalDatabase;

/**
 * Account modification interface.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class UpdateRentalDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private AdminInternalFrame parent;

    private RentalItem rentalItem;

    private JSpinner year;
    private JComboBox month;
    private JSpinner day;

    private Calendar calendar;

    private JButton save;
    private JButton cancel;

    /**
     * Instantiate the dialog.
     * 
     * @param parent
     *            the parent frame
     * @param rentalItem
     *            the rentalItem to be updated
     */
    public UpdateRentalDialog(AdminInternalFrame parent, RentalItem rentalItem)
    {
        super(AdminFrame.getInstance(), "Update Rental");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.rentalItem = rentalItem;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        calendar = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE));

        int yearNum = Calendar.getInstance().get(Calendar.YEAR);
        SpinnerModel model = new SpinnerNumberModel(yearNum, // initial value
                yearNum, // min
                yearNum + 100, // max
                1);

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Year:"), c);

        year = new JSpinner(model);
        c.gridx = 1;
        c.gridy = 0;
        this.add(year, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Month:"), c);

        String[] months = { "January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December" };
        month = new JComboBox(months);
        month.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        c.gridx = 1;
        c.gridy = 1;
        this.add(month, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Day:"), c);

        int dayNum = Calendar.getInstance().get(Calendar.DATE);
        SpinnerModel dayModel = new SpinnerNumberModel(dayNum, // initial value
                1,
                31,
                1);
        day = new JSpinner(dayModel);
        c.gridx = 1;
        c.gridy = 2;
        this.add(day, c);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        save = new JButton("Save");
        buttonPanel.add(save);

        cancel = new JButton("Cancel");
        buttonPanel.add(cancel);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        this.add(buttonPanel, c);

        this.pack();

        this.cancel.addActionListener(this);
        this.save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
            this.dispose();
        else if (e.getSource().equals(save))
        {
            int yearVal = (Integer) year.getValue();
            int monthVal = month.getSelectedIndex();
            int dayVal = (Integer) day.getValue();
            ;

            if ((yearVal == Calendar.getInstance().get(Calendar.YEAR))
                    && (monthVal < Calendar.getInstance().get(Calendar.MONTH)))
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid month! Date may not be before the current date.",
                                "Error updating rental",
                                JOptionPane.ERROR_MESSAGE);
            }
            else if ((yearVal == Calendar.getInstance().get(Calendar.YEAR))
                    && (monthVal == Calendar.getInstance().get(Calendar.MONTH))
                    && (dayVal < Calendar.getInstance().get(Calendar.DATE)))
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid day! Date may not be before the current date.",
                                "Error updating rental",
                                JOptionPane.ERROR_MESSAGE);
            }
            else if (dayVal > calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            {
                JOptionPane.showMessageDialog(null,
                        "Invalid day! Must be a real day.",
                        "Error updating rental", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Date dt = new Date(new GregorianCalendar(yearVal,
                        monthVal, dayVal).getTimeInMillis());

                try
                {
                    this.rentalItem = new RentalItem(
                            this.rentalItem.getRentalItemId(),
                            this.rentalItem.getItem(),
                            this.rentalItem.isRented(), dt,
                            this.rentalItem.isReturned());
                    RentalDatabase.updateRentalItem(rentalItem);

                    this.parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Unable to update rental!",
                            "Error updating rental", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
