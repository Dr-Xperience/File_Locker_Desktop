/****************************************************************************
    File Locker : Application to lock a file using AES Encryption with a password
    Copyright (C) 2013  Anubhav Arun <dr.xperience@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************/

package com.filelocker.gui;

/** *********************************************************************************
 * TITLE        : THE CLASS TO CREATE THE GUI OF THE APPLICATION
 * AUTHOR       : RK10R04A01;11000527;ANUBHAV ARUN GUPTA
 * DATE/TIME    : AD 2013.07.30.13.12
 * IDE       	: Kepler Release Build id: 20130614-0229
 * JAVA VERSION : 1.7.0_25
 * JRE          : java version "1.7.0_25" Java(TM) SE Runtime Environment (build 1.7.0_25-b16)
 * ************************************************************************************* */


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI
{

	JFrame vFrame;
	JPanel vPanel;


	public void frameBeg (String vTitle)
	{
		vFrame = new JFrame (vTitle);
		vFrame.setUndecorated (true);
		//vFrame.getRootPane ().setWindowDecorationStyle (JRootPane.FRAME);
		vFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

	public void frameEnd ()
	{
		vFrame.setBounds (200, 200, 300, 200);
		vFrame.setResizable (false);
		vFrame.setVisible (true);
	}



	JButton vLockButton = new JButton ("Lock/Unlock");

	JButton vCloseButton = new JButton ("Close");

	JLabel vBrowseLabel = new JLabel("Choose File");

	JButton vBrowseButton = new JButton ("Browse");

	JLabel vPasswordLabel = new JLabel("Enter Password");

	Font bigFont = new Font("serif",Font.BOLD,14);

	JPasswordField vPasswordField = new JPasswordField(20);

	JTextField vBrowseField = new JTextField (20);

	JFileChooser vFileChooser = new JFileChooser ();

	public JTextArea vNotificationArea = new JTextArea(14,20);
	JScrollPane vScroller = new JScrollPane(vNotificationArea);

}
