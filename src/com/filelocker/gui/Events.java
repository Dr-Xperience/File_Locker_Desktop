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
 * TITLE        : THE CLASS TO CONTROL THE APPLICATION EVENTS
 * AUTHOR       : RK10R04A01;11000527;ANUBHAV ARUN GUPTA
 * DATE/TIME    : AD 2013.07.30.13.12
 * IDE       	: Kepler Release Build id: 20130614-0229
 * JAVA VERSION : 1.7.0_25
 * JRE          : java version "1.7.0_25" Java(TM) SE Runtime Environment (build 1.7.0_25-b16)
 * ************************************************************************************* */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.codec.DecoderException;

import com.filelocker.encryption.AES_Encryption;

public class Events extends GUI
{


	public void make ()
	{
		//		PrintStream oldOut = System.out;

		PrintStream printStream = new PrintStream(new OutputStream()
		{  @Override
			public void write(byte[] buffer, int offset, int length) throws IOException
			{
			final String text = new String (buffer, offset, length);
			SwingUtilities.invokeLater(new Runnable ()
			{
				@Override
				public void run()
				{
					vNotificationArea.append (text);
				}
			});
			}

		@Override
		public void write(int b) throws IOException
		{
			write (new byte [] {(byte)b}, 0, 1);
		}

		});
		System.setOut(printStream);

		vPasswordField.setEchoChar('#');
		vPasswordLabel.setFont(bigFont);
		vBrowseLabel.setFont(bigFont);
		vNotificationArea.setVisible(false);
		vNotificationArea.setEditable(false);
		vScroller.setVisible(false);
		vScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		vPanel = new JPanel ();
		vPanel.add (vLockButton);
		vPanel.add (vCloseButton);
		vFrame.getContentPane ().add (BorderLayout.SOUTH, vPanel);

		vPanel = new JPanel ();
		vPanel.add(vPasswordLabel);
		vPanel.add (vPasswordField);
		vPanel.add(vBrowseLabel);
		vPanel.add (vBrowseField);
		vPanel.add (vBrowseButton);
		vPanel.add(vScroller);
		//		vPanel.setLayout (new BoxLayout (vPanel, BoxLayout.Y_AXIS));
		vFrame.getContentPane ().add (BorderLayout.CENTER, vPanel);


		vLockButton.addActionListener (new vLockButton_Click ());
		vCloseButton.addActionListener (new vCloseButton_Click ());

		vBrowseButton.addActionListener (new vBrowseButton_Click ());

	}


	class vLockButton_Click implements ActionListener
	{
		public void actionPerformed (ActionEvent eAction)
		{

			String myPassword = new String(vPasswordField.getPassword());

			vPasswordField.setText("");
			vNotificationArea.setVisible(true);
			vScroller.setVisible(true);
			vFrame.setBounds (200, 200, 300, 400);
			if(vBrowseField.getText().equals("")||myPassword.equals(""))
			{
				return;
			}
			else if(!(vBrowseField.getText().substring(vBrowseField.getText().lastIndexOf('.') + 1).equals("encrypt")))
			{

				AES_Encryption en = new AES_Encryption (myPassword);
				/*
				 * setup encryption cipher using password. print out iv and salt
				 */
				try
				{
					File vInFile= new File(vBrowseField.getText());

					if(vInFile.exists()==false)
					{
						throw new FileNotFoundException("File Not Found");
					}

					en.setupEncrypt ();
				}
				catch (InvalidKeyException ex)
				{
					ex.printStackTrace();
				}
				catch (NoSuchAlgorithmException ex)
				{
					ex.printStackTrace();
				}
				catch (InvalidKeySpecException ex)
				{
					ex.printStackTrace();
				}
				catch (NoSuchPaddingException ex)
				{
					ex.printStackTrace();
				}
				catch (InvalidParameterSpecException ex)
				{
					ex.printStackTrace();
				}
				catch (IllegalBlockSizeException ex)
				{
					ex.printStackTrace();
				}
				catch (BadPaddingException ex)
				{
					ex.printStackTrace();
				}
				catch (UnsupportedEncodingException ex)
				{
					ex.printStackTrace();
				}
				catch(FileNotFoundException ex)
				{
					ex.printStackTrace();
				}

				/*
				 * write out encrypted file
				 */
				try
				{
					File vInFile= new File(vBrowseField.getText());
					File vOutFile = new File(vBrowseField.getText()+".encrypt");

					if(vInFile.exists()==false)
					{
						throw new FileNotFoundException("File Not Found");
					}

					en.WriteEncryptedFile (vInFile, vOutFile);
					//					System.out.printf ("File encrypted to " + eoutput.getName () + "\niv:" + iv + "\nsalt:" + salt + "\n\n");

					vInFile.delete();
				}
				catch (IllegalBlockSizeException ex)
				{
					ex.printStackTrace();
				}
				catch (BadPaddingException ex)
				{
					ex.printStackTrace();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			else
			{

				/*
				 * decrypt file
				 */
				AES_Encryption dc = new AES_Encryption (myPassword);
				try
				{
					File vInFile= new File(vBrowseField.getText());

					if(vInFile.exists()==false)
					{
						throw new FileNotFoundException("File Not Found");
					}

					dc.setupDecrypt (vInFile);
				}
				catch (InvalidKeyException ex)
				{
					ex.printStackTrace();
				}
				catch (NoSuchAlgorithmException ex)
				{
					ex.printStackTrace();
				}
				catch (InvalidKeySpecException ex)
				{
					ex.printStackTrace();
				}
				catch (NoSuchPaddingException ex)
				{
					ex.printStackTrace();
				}
				catch (InvalidAlgorithmParameterException ex)
				{
					ex.printStackTrace();
				}
				catch (DecoderException ex)
				{
					ex.printStackTrace();
				} catch (IOException ex) {

					ex.printStackTrace();
				}


				/*
				 * write out decrypted file
				 */
				try
				{
					File vInFile= new File(vBrowseField.getText());
					File vOutFile = new File(vBrowseField.getText().substring(0, vBrowseField.getText().length() - 8));

					if(vInFile.exists()==false)
					{
						throw new FileNotFoundException("File Not Found");
					}

					dc.ReadEncryptedFile (vInFile, vOutFile);
					vInFile.delete();
					//					System.out.println ("decryption finished to " + doutput.getName ());
				}
				catch (IllegalBlockSizeException ex)
				{
					ex.printStackTrace();
				}
				catch (BadPaddingException ex)
				{
					ex.printStackTrace();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}


	class vCloseButton_Click implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{

			System.exit (0);
		}

	}
	class vBrowseButton_Click implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			int returnVal = vFileChooser.showOpenDialog (null);

			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File vFile = vFileChooser.getSelectedFile ();

				vFileChooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
				vBrowseField.setText (vFile.getAbsolutePath());

			}
		}

	}
}
