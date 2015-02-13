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

/**
 *
 */
package com.filelocker.driver;

/** *********************************************************************************
 * TITLE        : THE CLASS TO CONTROL THE APPLICATION EVENTS
 * AUTHOR       : RK10R04A01;11000527;ANUBHAV ARUN GUPTA
 * DATE/TIME    : AD 2013.07.30.13.12
 * IDE       	: Kepler Release Build id: 20130614-0229
 * JAVA VERSION : 1.7.0_25
 * JRE          : java version "1.7.0_25" Java(TM) SE Runtime Environment (build 1.7.0_25-b16)
 * ************************************************************************************* */

import com.filelocker.gui.Events;


public class TesterClass {

	public static void main( String arg[])
	{
		Events vGui= new Events();
		vGui.frameBeg("Encrption App");
		vGui.make();
		vGui.frameEnd();

	}
}
