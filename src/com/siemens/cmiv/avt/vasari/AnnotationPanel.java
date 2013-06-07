/*
Copyright (c) 2010, Siemens Corporate Research a Division of Siemens Corporation 
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.siemens.cmiv.avt.vasari;

import gme.cacore_cacore._3_2.edu_northwestern_radiology.AnatomicEntity;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservationCharacteristic;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ObjectFactory;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation.AnatomicEntityCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation.ImagingObservationCollection;

import java.awt.Color;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;


public class AnnotationPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	JTextField jAnnoTextField = null;
	JRadioButton jRadioButton00 = null;
	JRadioButton jRadioButton01 = null;
	
	JProgressBar jinforLabel = null;
	
	JLabel jLabel0 = null;	
	JLabel jLabel01 = null;

	JLabel jLabel1 = null;
	JComboBox jComboBoxLoc = null;
	
	JLabel jLabelElo = null;
	JComboBox jComboBoxElo = null;
	
	JTable jTabNodule = null;
	JRadioButton jRadioButton1 = null;
	JLabel jLabel2 = null;
	JRadioButton jRadioButton2 = null;
	JRadioButton jRadioButton3 = null;
	JRadioButton jRadioButton4 = null;
	JRadioButton jRadioButton5 = null;
	JLabel jLable3 = null;
	JTextArea jTextComment = null;
	JButton jBtnNew = null;
	JButton jBtnDelete = null;
	JButton jBtnPanZoom = null;
	JButton jBtnRotate = null;
	JButton jBtnReset = null;
	JButton jBtnPolygon = null;
	JButton jBtnContour = null;
	JButton jBtnTransform = null;
	
	JButton jBtnModify = null;
	JButton jBtnUndo = null;
	JButton jBtnClear = null;
	
	JButton jBtnLoad = null;
	JButton jBtnSave = null;
	JButton jBtnDone = null;
	
	JLabel jLesionLabel01 = null; 
	JLabel jLesionLabel02 = null;
	JLabel jLesionLabel03 = null;
	JLabel jLesionLabel04 = null;
	JLabel jLesionLabel05 = null;
	JLabel jLesionLabel06 = null;
	
	JLabel jLesionLabel11 = null; 
	JLabel jLesionLabel12 = null;
	JLabel jLesionLabel13 = null;
	JLabel jLesionLabel14 = null;
	JLabel jLesionLabel15 = null;
	JLabel jLesionLabel16 = null;
	
	JLabel jLesionLabel21 = null; 
	JLabel jLesionLabel22 = null;
	JLabel jLesionLabel23 = null;
	JLabel jLesionLabel24 = null;
	JLabel jLesionLabel25 = null;
	JLabel jLesionLabel26 = null;
	
	JComboBox jLesioncombo01 = null;
	JComboBox jLesioncombo02 = null;
	JComboBox jLesioncombo03 = null;
	JComboBox jLesioncombo04 = null;
	JComboBox jLesioncombo05 = null;
	JComboBox jLesioncombo06 = null;
	
	JComboBox jLesioncombo11 = null;
	JComboBox jLesioncombo12 = null;
	JComboBox jLesioncombo13 = null;
	JComboBox jLesioncombo14 = null;
	JComboBox jLesioncombo15 = null;
	JComboBox jLesioncombo16 = null;
	
	JComboBox jLesioncombo21 = null;
	JComboBox jLesioncombo22 = null;
	JComboBox jLesioncombo23 = null;
	JComboBox jLesioncombo24 = null;
	JComboBox jLesioncombo25 = null;
	JComboBox jLesioncombo26 = null;
	
	ButtonGroup roleGroup = new ButtonGroup();  // 
	ButtonGroup btnGroup = new ButtonGroup();  // 
	
	
	Color xipColor = new Color(51, 51, 102);
	Color textColor = new Color(212, 213, 234);
	Color labelColor = new Color(0, 153, 153);

	int index = 1;
	int roleInTrail = 0;
	
	int noduleLevel = 5;

	ArrayList<ArrayList<valuepair>> vasariContainer = new ArrayList<ArrayList<valuepair>>();
	ArrayList<JComboBox> attributes = new ArrayList<JComboBox>();
	
	private ObjectFactory factory = new ObjectFactory();
	
	public AnnotationPanel(){
		super();
		initiContainer();
		initialize();
	}
	private void initiContainer(){
		ArrayList<valuepair> tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("77", "occipital lobe"));
		tmp.add(new valuepair("73", "frontal lobe"));
		tmp.add(new valuepair("76", "parietal lobe"));
		tmp.add(new valuepair("79", "brain stem"));
		tmp.add(new valuepair("78", "cerebellum"));
		tmp.add(new valuepair("75", "insula"));
		tmp.add(new valuepair("74", "temporal lobe"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("87", "speech receptive center involvement"));
		tmp.add(new valuepair("89", "vision center involvement"));
		tmp.add(new valuepair("88", "motor center involvement"));
		tmp.add(new valuepair("86", "speech motor center involvement"));
		tmp.add(new valuepair("85", "no eloquent brain involvement"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("94", "ependymal invasion absent"));
		tmp.add(new valuepair("93", "ependymal invasion present"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("71", "smooth non-enhancing margin"));
		tmp.add(new valuepair("70", "non-enhancing margin not applicable"));
		tmp.add(new valuepair("72", "irregular non-enhancing margin"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("92", "infiltrative T1/FLAIR ratio"));
		tmp.add(new valuepair("90", "expansive T1/FLAIR ratio"));
		tmp.add(new valuepair("95", "T1/FLAIR ratio not applicable"));
		tmp.add(new valuepair("91", "mixed T1/FLAIR ratio"));
		
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("59", "greater than 95% enhancement"));
		tmp.add(new valuepair("57", "34-67% enhancement"));
		tmp.add(new valuepair("56", "6-33% enhancement"));
		tmp.add(new valuepair("58", "68-95% enhancement"));
		tmp.add(new valuepair("55", "less than 5% enhancement"));
		tmp.add(new valuepair("54", "no enhancement"));
		tmp.add(new valuepair("53", "enhancement indeterminate"));
		tmp.add(new valuepair("60", "100% enhancement"));
		tmp.add(new valuepair("52", "proportion of enhancement not applicable"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("64", "center epicenter"));
		tmp.add(new valuepair("66", "left epicenter"));
		tmp.add(new valuepair("65", "right epicenter"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("61", "ncet tumor does cross midline"));
		tmp.add(new valuepair("63", "ncet tumor crosses midline not applicable"));
		tmp.add(new valuepair("62", "ncet tumor does not cross midline"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("3", "enhancing tumor crosses midline not applicable"));
		tmp.add(new valuepair("1", "enhancing tumor does cross midline"));
		tmp.add(new valuepair("2", "enhancing tumor does not cross midline"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("34", "100% edema"));
		tmp.add(new valuepair("32", "68-95% edema"));
		tmp.add(new valuepair("28", "no edema"));
		tmp.add(new valuepair("33", "greater than 95% edema"));
		tmp.add(new valuepair("31", "34-67% edema"));
		tmp.add(new valuepair("29", "less than 5% edema"));
		tmp.add(new valuepair("27", "edema indeterminate"));
		tmp.add(new valuepair("30", "6-33% edema"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("49", "68-95% necrosis"));
		tmp.add(new valuepair("48", "34-67% necrosis"));
		tmp.add(new valuepair("43", "proportion of necrosis not applicable"));
		tmp.add(new valuepair("46", "less than 5% necrosis"));
		tmp.add(new valuepair("45", "no necrosis"));
		tmp.add(new valuepair("44", "necrosis indeterminate"));
		tmp.add(new valuepair("51", "100% necrosis"));
		tmp.add(new valuepair("50", "greater than 95% necrosis"));
		tmp.add(new valuepair("47", "6-33% necrosis"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("19", "no ncet"));
		tmp.add(new valuepair("23", "68-95% ncet"));
		tmp.add(new valuepair("24", "greater than 95% ncet"));
		tmp.add(new valuepair("17", "proportion of ncet not applicable"));
		tmp.add(new valuepair("20", "less than 5% ncet"));
		tmp.add(new valuepair("25", "100% ncet"));
		tmp.add(new valuepair("18", "ncet indeterminate"));
		tmp.add(new valuepair("22", "34-67% ncet"));
		tmp.add(new valuepair("21", "6-33% ncet"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("10", "poorly-defined enhancing margin"));
		tmp.add(new valuepair("12", "enhancing margin definition not applicable"));
		tmp.add(new valuepair("11", "well-defined enhancing margin"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("80", "no enhancing margin"));
		tmp.add(new valuepair("81", "thin enhancing margin"));
		tmp.add(new valuepair("82", "thick enhancing margin"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("15", "multicentric"));
		tmp.add(new valuepair("16", "multifocal"));
		tmp.add(new valuepair("14", "gliomatosis"));
		tmp.add(new valuepair("13", "morphology region not applicable"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("69", "marked/avid enhancement"));
		tmp.add(new valuepair("68", "mild/minimal enhancement"));
		tmp.add(new valuepair("67", "no enhancement"));
		vasariContainer.add(tmp);
		
		tmp = new ArrayList<valuepair>();
		tmp.add(new valuepair("0", "<select from list>"));
		tmp.add(new valuepair("6", "deep wm invasion present"));
		tmp.add(new valuepair("7", "deep wm invasion absent"));
		vasariContainer.add(tmp);
		
	}
	private void setComboboxvalue(JComboBox jcombo, ArrayList<valuepair> hashvalue){
		for(int i = 0; i < hashvalue.size(); i++){
			jcombo.addItem(hashvalue.get(i).getValue());
		}
	}
	@SuppressWarnings("unchecked")
	private void initialize() {
		setLayout(null);		
		JPanel jTitle = new JPanel();
		jTitle.setBackground(xipColor);
		
		jTitle.setBounds(new Rectangle(0, 0, 350, 120));
		jTitle.setLayout(null);
		
		jLabel0 = new JLabel();
		jLabel0.setBounds(new Rectangle(104, 13, 100, 50));
		jLabel0.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
		jLabel0.setText("AVT");
		jLabel0.setBackground(xipColor);
		jLabel0.setForeground(Color.WHITE);
		jTitle.add(jLabel0, null);
		
		jLabel01 = new JLabel();
		jLabel01.setBounds(new Rectangle(30, 53, 300, 50));
		jLabel01.setFont(new Font("Arial", Font.BOLD, 25));
		jLabel01.setText("TCGA Annotation Tool");
		jLabel01.setBackground(xipColor);
		jLabel01.setForeground(labelColor);
		jTitle.add(jLabel01, null);	
		
		add(jTitle);
		
		JPanel jAnnotationPanel = new JPanel();
		jAnnotationPanel.setBackground(xipColor);
		jAnnotationPanel.setBounds(new Rectangle(0, 150, 344, 95));
		jAnnotationPanel.setLayout(null);
		
		TitledBorder anntationborder;
		anntationborder = BorderFactory.createTitledBorder("Annotation");
		anntationborder.setTitleColor(Color.WHITE);
		jAnnotationPanel.setBorder(anntationborder);
		
		JLabel jAnnoNameLabel = new JLabel();
		jAnnoNameLabel.setBounds(new Rectangle(17, 24, 50, 17));
		jAnnoNameLabel.setText("Name:");
		jAnnoNameLabel.setBackground(xipColor);
		jAnnoNameLabel.setForeground(Color.WHITE);
		jAnnotationPanel.add(jAnnoNameLabel, null);
		
		jAnnoTextField = new JTextField();
		jAnnoTextField.setBounds(new Rectangle(91, 20, 220, 23));
		jAnnoTextField.setText("Annotation1");
		jAnnoTextField.setFont(new Font("",Font.BOLD,12));
		jAnnoTextField.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		jAnnotationPanel.add(jAnnoTextField, null);
		
		JLabel jAnnoTypeLabel = new JLabel();
		jAnnoTypeLabel.setBounds(new Rectangle(17, 55, 50, 17));
		jAnnoTypeLabel.setText("Type:");
		jAnnoTypeLabel.setBackground(xipColor);
		jAnnoTypeLabel.setForeground(Color.WHITE);
		jAnnotationPanel.add(jAnnoTypeLabel, null);
		
		ButtonGroup group = new ButtonGroup();
		
		jRadioButton00 = new JRadioButton();
		jRadioButton00.setBounds(new Rectangle(89, 50, 237, 23));
		jRadioButton00.setText("Brain tumor baseline target lesion");
		jRadioButton00.setBackground(xipColor);
		jRadioButton00.setForeground(Color.WHITE);
//		jRadioButton00.setSelected(true);
		group.add(jRadioButton00);
		jAnnotationPanel.add(jRadioButton00, null);
		
		jRadioButton01 = new JRadioButton();
		jRadioButton01.setBounds(new Rectangle(89, 68, 237, 23));
		jRadioButton01.setText("Brain tumor follow-up target lesion");
		jRadioButton01.setBackground(xipColor);
		jRadioButton01.setForeground(Color.WHITE);
		group.add(jRadioButton01);
		jAnnotationPanel.add(jRadioButton01, null);
		
		add(jAnnotationPanel);
		
		TitledBorder border;
		border = BorderFactory.createTitledBorder("AnatomicEntity");
		border.setTitleColor(Color.WHITE);
		
		JPanel jAnnotation = new JPanel();
		//jAnnotation.setBorder(border);
		jAnnotation.setBounds(new Rectangle(0, 250, 344, 455));
		jAnnotation.setLayout(null);
		jAnnotation.setBackground(xipColor);
		
		JPanel jAnatomicpanel = new JPanel();
		jAnatomicpanel.setBorder(border);
		jAnatomicpanel.setBounds(new Rectangle(0, 0, 344, 50));
		jAnatomicpanel.setLayout(null);
		jAnatomicpanel.setBackground(xipColor);
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(17, 24, 71, 17));
		jLabel1.setText("Location:");
		jLabel1.setBackground(xipColor);
		jLabel1.setForeground(Color.WHITE);
		jAnatomicpanel.add(jLabel1, null);
		
		jComboBoxLoc = new JComboBox();
		jComboBoxLoc.setBounds(new Rectangle(91, 20, 220, 23));
		jComboBoxLoc.setEditable(false);
		setComboboxvalue(jComboBoxLoc, vasariContainer.get(0));
		attributes.add(jComboBoxLoc);

		jAnatomicpanel.add(jComboBoxLoc, null);
		
		jAnnotation.add(jAnatomicpanel, null);
		
		JPanel jImageObserpanel = new JPanel();
		jImageObserpanel.setBounds(new Rectangle(0, 52, 344, 403));
		jImageObserpanel.setLayout(null);
		jImageObserpanel.setBackground(xipColor);
		
		JPanel jImageObserpanelElo = new JPanel();
		jImageObserpanelElo.setBounds(new Rectangle(5, 15, 330, 55));
		jImageObserpanelElo.setLayout(null);
		jImageObserpanelElo.setBackground(xipColor);
		jImageObserpanel.add(jImageObserpanelElo,null);
		
		jLabelElo = new JLabel();
		jLabelElo.setBounds(new Rectangle(7, 10, 300, 17));
		jLabelElo.setText("Eloquent Brain Involvement");
		jLabelElo.setBackground(xipColor);
		jLabelElo.setForeground(Color.WHITE);
		jImageObserpanelElo.add(jLabelElo,null);
		
		jComboBoxElo = new JComboBox();
		jComboBoxElo.setBounds(new Rectangle(7, 30, 300, 23));
		jComboBoxElo.setEditable(false);
		setComboboxvalue(jComboBoxElo, vasariContainer.get(1));
		jImageObserpanelElo.add(jComboBoxElo,null);
		attributes.add(jComboBoxElo);
		
		JTabbedPane jTabPanel = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		jTabPanel.setBounds(new Rectangle(2, 70, 340, 330));
		jImageObserpanel.add(jTabPanel,null);
		
		JPanel jLesionpanel1 = new JPanel();
		jLesionpanel1.setLayout(null);
		jLesionpanel1.setBackground(xipColor);
		
		int offset = 53;
		ArrayList rectList = new ArrayList();
		for(int i = 0; i < 6; i ++){
			rectList.add(new Rectangle(7, 10 + (i * offset), 300, 17));
			rectList.add(new Rectangle(7, 30 + (i * offset), 300, 23));
		}
		jLesionLabel01 = new JLabel();
		jLesionLabel01.setBounds((Rectangle)rectList.get(0));
		jLesionLabel01.setText("Ependymal Invasion");
		jLesionLabel01.setBackground(xipColor);
		jLesionLabel01.setForeground(Color.WHITE);
		
		jLesioncombo01 = new JComboBox();
		jLesioncombo01.setBounds((Rectangle)rectList.get(1));
		jLesioncombo01.setEditable(false);
		setComboboxvalue(jLesioncombo01, vasariContainer.get(2));
		attributes.add(jLesioncombo01);
		
		jLesionLabel02 = new JLabel();
		jLesionLabel02.setBounds((Rectangle)rectList.get(2));
		jLesionLabel02.setText("Definition of the Non-Enhancing Margin");
		jLesionLabel02.setBackground(xipColor);
		jLesionLabel02.setForeground(Color.WHITE);
		
		jLesioncombo02 = new JComboBox();
		jLesioncombo02.setBounds((Rectangle)rectList.get(3));
		jLesioncombo02.setEditable(false);
		setComboboxvalue(jLesioncombo02, vasariContainer.get(3));
		attributes.add(jLesioncombo02);
		
		jLesionLabel03 = new JLabel();
		jLesionLabel03.setBounds((Rectangle)rectList.get(4));
		jLesionLabel03.setText("T1/FLAIR Ratio");
		jLesionLabel03.setBackground(xipColor);
		jLesionLabel03.setForeground(Color.WHITE);
		
		jLesioncombo03 = new JComboBox();
		jLesioncombo03.setBounds((Rectangle)rectList.get(5));
		jLesioncombo03.setEditable(false);
		setComboboxvalue(jLesioncombo03, vasariContainer.get(4));
		attributes.add(jLesioncombo03);
		
		jLesionLabel04 = new JLabel();
		jLesionLabel04.setBounds((Rectangle)rectList.get(6));
		jLesionLabel04.setText("Proportion Enhancing");
		jLesionLabel04.setBackground(xipColor);
		jLesionLabel04.setForeground(Color.WHITE);
		
		jLesioncombo04 = new JComboBox();
		jLesioncombo04.setBounds((Rectangle)rectList.get(7));
		jLesioncombo04.setEditable(false);
		setComboboxvalue(jLesioncombo04, vasariContainer.get(5));
		attributes.add(jLesioncombo04);

		jLesionLabel05 = new JLabel();
		jLesionLabel05.setBounds((Rectangle)rectList.get(8));
		jLesionLabel05.setText("Side of Tumor Epicenter");
		jLesionLabel05.setBackground(xipColor);
		jLesionLabel05.setForeground(Color.WHITE);
		
		jLesioncombo05 = new JComboBox();
		jLesioncombo05.setBounds((Rectangle)rectList.get(9));
		jLesioncombo05.setEditable(false);
		setComboboxvalue(jLesioncombo05, vasariContainer.get(6));
		attributes.add(jLesioncombo05);
		
		/*jLesionLabel06 = new JLabel();
		jLesionLabel06.setBounds((Rectangle)rectList.get(10));
		jLesionLabel06.setText("Thickness of the Enhancing Margin");
		jLesionLabel06.setBackground(xipColor);
		jLesionLabel06.setForeground(Color.WHITE);
		
		jLesioncombo06 = new JComboBox();
		jLesioncombo06.setBounds((Rectangle)rectList.get(11));
		jLesioncombo06.setEditable(false);
		setComboboxvalue(jLesioncombo06, vasariContainer.get(6));*/
	
		jLesionpanel1.add(jLesionLabel01, null);
		jLesionpanel1.add(jLesioncombo01, null);
		jLesionpanel1.add(jLesionLabel02, null);
		jLesionpanel1.add(jLesioncombo02, null);
		jLesionpanel1.add(jLesionLabel03, null);
		jLesionpanel1.add(jLesioncombo03, null);
		jLesionpanel1.add(jLesionLabel04, null);
		jLesionpanel1.add(jLesioncombo04, null);
		jLesionpanel1.add(jLesionLabel05, null);
		jLesionpanel1.add(jLesioncombo05, null);
		//jLesionpanel1.add(jLesionLabel06, null);
		//jLesionpanel1.add(jLesioncombo06, null);

		JPanel jLesionpanel2 = new JPanel();
		jLesionpanel2.setLayout(null);
		jLesionpanel2.setBackground(xipColor);
		
		jLesionLabel11 = new JLabel();
		jLesionLabel11.setBounds((Rectangle)rectList.get(0));
		jLesionLabel11.setText("nCET Tumor Crosses Midline");
		jLesionLabel11.setBackground(xipColor);
		jLesionLabel11.setForeground(Color.WHITE);
		
		jLesioncombo11 = new JComboBox();
		jLesioncombo11.setBounds((Rectangle)rectList.get(1));
		jLesioncombo11.setEditable(false);
		setComboboxvalue(jLesioncombo11, vasariContainer.get(7));
		attributes.add(jLesioncombo11);
		
		jLesionLabel12 = new JLabel();
		jLesionLabel12.setBounds((Rectangle)rectList.get(2));
		jLesionLabel12.setText("Enhancing Tumor Crosses Midline");
		jLesionLabel12.setBackground(xipColor);
		jLesionLabel12.setForeground(Color.WHITE);
		
		jLesioncombo12 = new JComboBox();
		jLesioncombo12.setBounds((Rectangle)rectList.get(3));
		jLesioncombo12.setEditable(false);
		setComboboxvalue(jLesioncombo12, vasariContainer.get(8));
		attributes.add(jLesioncombo12);
		
		jLesionLabel13 = new JLabel();
		jLesionLabel13.setBounds((Rectangle)rectList.get(4));
		jLesionLabel13.setText("Proportion of Edema");
		jLesionLabel13.setBackground(xipColor);
		jLesionLabel13.setForeground(Color.WHITE);
		
		jLesioncombo13 = new JComboBox();
		jLesioncombo13.setBounds((Rectangle)rectList.get(5));
		jLesioncombo13.setEditable(false);
		setComboboxvalue(jLesioncombo13, vasariContainer.get(9));
		attributes.add(jLesioncombo13);
		
		jLesionLabel14 = new JLabel();
		jLesionLabel14.setBounds((Rectangle)rectList.get(6));
		jLesionLabel14.setText("Proportion Necrosis");
		jLesionLabel14.setBackground(xipColor);
		jLesionLabel14.setForeground(Color.WHITE);
		
		jLesioncombo14 = new JComboBox();
		jLesioncombo14.setBounds((Rectangle)rectList.get(7));
		jLesioncombo14.setEditable(false);
		setComboboxvalue(jLesioncombo14, vasariContainer.get(10));
		attributes.add(jLesioncombo14);

		jLesionLabel15 = new JLabel();
		jLesionLabel15.setBounds((Rectangle)rectList.get(8));
		jLesionLabel15.setText("Proportion nCET");
		jLesionLabel15.setBackground(xipColor);
		jLesionLabel15.setForeground(Color.WHITE);
		
		jLesioncombo15 = new JComboBox();
		jLesioncombo15.setBounds((Rectangle)rectList.get(9));
		jLesioncombo15.setEditable(false);
		setComboboxvalue(jLesioncombo15, vasariContainer.get(11));
		attributes.add(jLesioncombo15);
		
		/*jLesionLabel16 = new JLabel();
		jLesionLabel16.setBounds((Rectangle)rectList.get(10));
		jLesionLabel16.setText("Calvarial Remodeling");
		jLesionLabel16.setBackground(xipColor);
		jLesionLabel16.setForeground(Color.WHITE);
		
		jLesioncombo16 = new JComboBox();
		jLesioncombo16.setBounds((Rectangle)rectList.get(11));
		jLesioncombo16.setEditable(false);
		setComboboxvalue(jLesioncombo16, vasariContainer.get(12));*/
		
		jLesionpanel2.add(jLesionLabel11, null);
		jLesionpanel2.add(jLesioncombo11, null);
		jLesionpanel2.add(jLesionLabel12, null);
		jLesionpanel2.add(jLesioncombo12, null);
		jLesionpanel2.add(jLesionLabel13, null);
		jLesionpanel2.add(jLesioncombo13, null);
		jLesionpanel2.add(jLesionLabel14, null);
		jLesionpanel2.add(jLesioncombo14, null);
		jLesionpanel2.add(jLesionLabel15, null);
		jLesionpanel2.add(jLesioncombo15, null);
		//jLesionpanel2.add(jLesionLabel16, null);
		//jLesionpanel2.add(jLesioncombo16, null);
		
		JPanel jLesionpanel3 = new JPanel();
		jLesionpanel3.setLayout(null);
		jLesionpanel3.setBackground(xipColor);
		
		jLesionLabel21 = new JLabel();
		jLesionLabel21.setBounds((Rectangle)rectList.get(0));
		jLesionLabel21.setText("Definition of the Enhancing Margin");
		jLesionLabel21.setBackground(xipColor);
		jLesionLabel21.setForeground(Color.WHITE);
		
		jLesioncombo21 = new JComboBox();
		jLesioncombo21.setBounds((Rectangle)rectList.get(1));
		jLesioncombo21.setEditable(false);
		setComboboxvalue(jLesioncombo21, vasariContainer.get(12));
		attributes.add(jLesioncombo21);
		
		jLesionLabel22 = new JLabel();
		jLesionLabel22.setBounds((Rectangle)rectList.get(2));
		jLesionLabel22.setText("Thickness of the Enhancing Margin");
		jLesionLabel22.setBackground(xipColor);
		jLesionLabel22.setForeground(Color.WHITE);
		
		jLesioncombo22 = new JComboBox();
		jLesioncombo22.setBounds((Rectangle)rectList.get(3));
		jLesioncombo22.setEditable(false);
		setComboboxvalue(jLesioncombo22, vasariContainer.get(13));
		attributes.add(jLesioncombo22);
		
		jLesionLabel23 = new JLabel();
		jLesionLabel23.setBounds((Rectangle)rectList.get(4));
		jLesionLabel23.setText("Multifocal or Multicentric");
		jLesionLabel23.setBackground(xipColor);
		jLesionLabel23.setForeground(Color.WHITE);
		
		jLesioncombo23 = new JComboBox();
		jLesioncombo23.setBounds((Rectangle)rectList.get(5));
		jLesioncombo23.setEditable(false);
		setComboboxvalue(jLesioncombo23, vasariContainer.get(14));
		attributes.add(jLesioncombo23);
		
		jLesionLabel24 = new JLabel();
		jLesionLabel24.setBounds((Rectangle)rectList.get(6));
		jLesionLabel24.setText("Enhancement Quality");
		jLesionLabel24.setBackground(xipColor);
		jLesionLabel24.setForeground(Color.WHITE);
		
		jLesioncombo24 = new JComboBox();
		jLesioncombo24.setBounds((Rectangle)rectList.get(7));
		jLesioncombo24.setEditable(false);
		setComboboxvalue(jLesioncombo24, vasariContainer.get(15));
		attributes.add(jLesioncombo24);

		jLesionLabel25 = new JLabel();
		jLesionLabel25.setBounds((Rectangle)rectList.get(8));
		jLesionLabel25.setText("Deep WM Invasion");
		jLesionLabel25.setBackground(xipColor);
		jLesionLabel25.setForeground(Color.WHITE);
		
		jLesioncombo25 = new JComboBox();
		jLesioncombo25.setBounds((Rectangle)rectList.get(9));
		jLesioncombo25.setEditable(false);
		setComboboxvalue(jLesioncombo25, vasariContainer.get(16));
		attributes.add(jLesioncombo25);
		
		/*jLesionLabel26 = new JLabel();
		jLesionLabel26.setBounds((Rectangle)rectList.get(10));
		jLesionLabel26.setText("Hemorrhage");
		jLesionLabel26.setBackground(xipColor);
		jLesionLabel26.setForeground(Color.WHITE);
		
		jLesioncombo26 = new JComboBox();
		jLesioncombo26.setBounds((Rectangle)rectList.get(11));
		jLesioncombo26.setEditable(false);
		setComboboxvalue(jLesioncombo26, vasariContainer.get(18));*/

		jLesionpanel3.add(jLesionLabel21, null);
		jLesionpanel3.add(jLesioncombo21, null);
		jLesionpanel3.add(jLesionLabel22, null);
		jLesionpanel3.add(jLesioncombo22, null);
		jLesionpanel3.add(jLesionLabel23, null);
		jLesionpanel3.add(jLesioncombo23, null);
		jLesionpanel3.add(jLesionLabel24, null);
		jLesionpanel3.add(jLesioncombo24, null);
		jLesionpanel3.add(jLesionLabel25, null);
		jLesionpanel3.add(jLesioncombo25, null);
		//jLesionpanel3.add(jLesionLabel26, null);
		//jLesionpanel3.add(jLesioncombo26, null);
		

		TitledBorder border1;
		border1 = BorderFactory.createTitledBorder("Image Observations");
		border1.setTitleColor(Color.WHITE);
		
		TitledBorder borderTumor;
		borderTumor = BorderFactory.createTitledBorder("Tumor");
		borderTumor.setTitleColor(Color.WHITE);
		
		jImageObserpanel.setBorder(border1);
		jTabPanel.addTab("Characteristic1", jLesionpanel1);
		jTabPanel.addTab("Characteristic2", jLesionpanel2);
		jTabPanel.addTab("Characteristic3", jLesionpanel3);
		jTabPanel.setBorder(borderTumor);
		jTabPanel.setSelectedIndex(0);

		jAnnotation.add(jImageObserpanel, null);
		
		add(jAnnotation);
    	
		border = BorderFactory.createTitledBorder("Tool");
		border.setTitleColor(Color.WHITE);
		
		JPanel jTool = new JPanel();
		jTool.setBorder(border);
		jTool.setBounds(new Rectangle(0, 715, 344, 140));
		jTool.setLayout(null);
		jTool.setBackground(xipColor);
   	
		jBtnPanZoom = new JButton();
    	jBtnPanZoom.setBounds(new Rectangle(11, 25, 100, 30));
    	jBtnPanZoom.setText("Pan/Zoom");
    	jBtnPanZoom.setBackground(xipColor);
    	jBtnPanZoom.setForeground(Color.WHITE);
    	jBtnPanZoom.setEnabled(true);
		jTool.add(jBtnPanZoom, null);
		
		jBtnRotate = new JButton();
		jBtnRotate.setBounds(new Rectangle(121, 25, 100, 30));
		jBtnRotate.setText("Rotate");
		jBtnRotate.setBackground(xipColor);
		jBtnRotate.setForeground(Color.WHITE);
		jBtnRotate.setEnabled(true);
		jTool.add(jBtnRotate, null);
		
		jBtnReset = new JButton();
		jBtnReset.setBounds(new Rectangle(228, 25, 100, 30));
		jBtnReset.setText("Reset");
		jBtnReset.setBackground(xipColor);
		jBtnReset.setForeground(Color.WHITE);
		jBtnReset.setEnabled(true);
		jTool.add(jBtnReset, null);
		
		jBtnContour = new JButton();
		jBtnContour.setBounds(new Rectangle(11, 62, 100, 30));
		jBtnContour.setText("Contour");
		jBtnContour.setBackground(xipColor);
		jBtnContour.setForeground(Color.WHITE);
		jBtnContour.setEnabled(true);
		jTool.add(jBtnContour, null);
		
		jBtnPolygon = new JButton();
		jBtnPolygon.setBounds(new Rectangle(121, 62, 100, 30));
		jBtnPolygon.setText("Polygon");
		jBtnPolygon.setBackground(xipColor);
		jBtnPolygon.setForeground(Color.WHITE);
		jBtnPolygon.setEnabled(true);
		jTool.add(jBtnPolygon, null);
		
		jBtnClear = new JButton();
		jBtnClear.setBounds(new Rectangle(228, 62, 100, 30));
		jBtnClear.setText("Clear");
		jBtnClear.setBackground(xipColor);
		jBtnClear.setForeground(Color.WHITE);
		jBtnClear.setEnabled(true);
		jTool.add(jBtnClear, null);
		
		jBtnTransform = new JButton();
		jBtnTransform.setBounds(new Rectangle(11, 99, 100, 30));
		jBtnTransform.setText("Transform");
		jBtnTransform.setBackground(xipColor);
		jBtnTransform.setForeground(Color.WHITE);
		jBtnTransform.setEnabled(true);
		jTool.add(jBtnTransform, null);
		
		jBtnModify = new JButton();
		jBtnModify.setBounds(new Rectangle(121, 99, 100, 30));
		jBtnModify.setText("Modify");
		jBtnModify.setBackground(xipColor);
		jBtnModify.setForeground(Color.WHITE);
		jBtnModify.setEnabled(true);
		jTool.add(jBtnModify, null);
		
		jBtnUndo = new JButton();
		jBtnUndo.setBounds(new Rectangle(228, 99, 100, 30));
		jBtnUndo.setText("Undo");
		jBtnUndo.setBackground(xipColor);
		jBtnUndo.setForeground(Color.WHITE);
		jBtnUndo.setEnabled(true);
		jTool.add(jBtnUndo, null);

		add(jTool);
		
		jBtnSave = new JButton();
		jBtnSave.setBounds(new Rectangle(50, 885, 100, 30));
		jBtnSave.setText("Save");
		jBtnSave.setBackground(xipColor);
		jBtnSave.setForeground(Color.WHITE);
		jBtnSave.setEnabled(true);
		add(jBtnSave);
		
//		jBtnDone = new JButton();
//		jBtnDone.setBounds(new Rectangle(190, 885, 100, 30));
//		jBtnDone.setText("Done");
//		jBtnDone.setBackground(xipColor);
//		jBtnDone.setForeground(Color.WHITE);
//		jBtnDone.setEnabled(true);
//		add(jBtnDone);
        
		jBtnLoad = new JButton();
		jBtnLoad.setBounds(new Rectangle(190, 885, 100, 30));
		jBtnLoad.setText("Load");
		jBtnLoad.setBackground(xipColor);
		jBtnLoad.setForeground(Color.WHITE);
		jBtnLoad.setEnabled(true);
		add(jBtnLoad);		

		jinforLabel = new JProgressBar();
		jinforLabel.setBounds(new Rectangle(10, 950, 300, 25));
		jinforLabel.setBackground(new Color(156, 162, 189));
		jinforLabel.setStringPainted(true);	    
		jinforLabel.setForeground(xipColor);
		jinforLabel.setEnabled(true);
		jinforLabel.setString("Ready");
		add(jinforLabel);
		
		setBackground(xipColor);
	}
	public void setinforLabel(String str){
		jinforLabel.setString(str);
	}
	public void clearinforLabel(){
		jinforLabel.setString(null);
	}
	public boolean checkSelectItems(){
		Object[] options = {"OK"}; 
		if (getAnnotationName().isEmpty()){
            JOptionPane.showOptionDialog(this, "Please set \"Annotation Name\" ",
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
            return false;			
		}
		if (!jRadioButton00.isSelected() && !jRadioButton01.isSelected()){
            JOptionPane.showOptionDialog(this, "Please select \"Annotation Type\" Item",
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
            return false;
		}			
		if(0 == jComboBoxLoc.getSelectedIndex()){
            JOptionPane.showOptionDialog(this, "Please select \"AnatomicEntity\" Item",
                            "Warning",  
                            JOptionPane.OK_OPTION,  
                            JOptionPane.WARNING_MESSAGE,  
                            null,  
                            options,  
                            options[0]);
			return false;
		}
		if(0 == jComboBoxElo.getSelectedIndex()){
            JOptionPane.showOptionDialog(this, "Please select \"Eloquent Brain Involvement\" Item",
                            "Warning",  
                            JOptionPane.OK_OPTION,  
                            JOptionPane.WARNING_MESSAGE,  
                            null,  
                            options,  
                            options[0]);
			return false;
		}
		if(0 == jLesioncombo01.getSelectedIndex()){
            JOptionPane.showOptionDialog(this, "Please select \"Ependymal Invasion\" Item",
                            "Warning",  
                            JOptionPane.OK_OPTION,  
                            JOptionPane.WARNING_MESSAGE,  
                            null,  
                            options,  
                            options[0]);
			return false;
		}
		if(0 == jLesioncombo02.getSelectedIndex()){
			 JOptionPane.showOptionDialog(this, "Please select \"Definition of the Non-Enhancing Margin\" Item",  
                     "Warning",  
                     JOptionPane.OK_OPTION,  
                     JOptionPane.WARNING_MESSAGE,  
                     null,  
                     options,  
                     options[0]);
			return false;
		}
		if(0 == jLesioncombo03.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"T1/FLAIR Ratio\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo04.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Proportion Enhancing\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo05.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Side of Tumor Epicenter\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		/*if(0 == jLesioncombo06.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Thickness of the Enhancing Margin\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}*/
		if(0 == jLesioncombo11.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"nCET Tumor Crosses Midline\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo12.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Enhancing Tumor Crosses Midline\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo13.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Proportion of Edema\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo14.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Proportion Necrosis\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo15.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Proportion nCET\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		/*if(0 == jLesioncombo16.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Calvarial Remodeling\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}*/
		if(0 == jLesioncombo21.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Definition of the Enhancing Margin\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo22.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Thickness of the Enhancing Margin\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo23.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Multifocal or Multicentric\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo24.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Enhancement Quality\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		if(0 == jLesioncombo25.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Deep WM Invasion\" Item",  
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}
		/*if(0 == jLesioncombo26.getSelectedIndex()){
			JOptionPane.showOptionDialog(this, "Please select \"Hemorrhage\" Item",
                    "Warning",  
                    JOptionPane.OK_OPTION,  
                    JOptionPane.WARNING_MESSAGE,  
                    null,  
                    options,  
                    options[0]);
			return false;
		}*/
		return true;
	}
	
    public AnatomicEntityCollection assmbleAnatomicEntity(){
    	AnatomicEntityCollection anatomicentityCollection = factory.createAnnotationAnatomicEntityCollection();
    	
    	List<AnatomicEntity> anatomicentityList = anatomicentityCollection.getAnatomicEntity();
    	AnatomicEntity anatomicentity = factory.createAnatomicEntity();
    	
    	ArrayList<valuepair> locList = vasariContainer.get(0);
    	valuepair locvalue = locList.get(jComboBoxLoc.getSelectedIndex());
    	anatomicentity.setId(BigInteger.ZERO);
    	anatomicentity.setCodeValue(locvalue.getKey());
    	anatomicentity.setCodeMeaning(locvalue.getValue());
    	anatomicentity.setCodingSchemeDesignator("VASARI");
    	anatomicentityList.add(anatomicentity);
    	
    	return anatomicentityCollection;
    }
    
    public ImagingObservationCollection assmbleImageObservations(){
    	ImagingObservationCollection imagingObservationCollection = factory.createAnnotationImagingObservationCollection();
    	
    	List<ImagingObservation> imagingObservationList = imagingObservationCollection.getImagingObservation();
    	ImagingObservation imagingObservation1 = factory.createImagingObservation();
    	imagingObservation1.setCodeMeaning("tumor");
    	imagingObservation1.setCodeValue("84");
    	imagingObservation1.setCodingSchemeDesignator("VASARI");
    	imagingObservation1.setId(BigInteger.ZERO);
    	imagingObservationList.add(imagingObservation1);
    	
    	ImagingObservation imagingObservation2 = factory.createImagingObservation();
    	ArrayList<valuepair> bloList = vasariContainer.get(1);
    	valuepair blovalue = bloList.get(jComboBoxElo.getSelectedIndex());
    	imagingObservation2.setCodeMeaning(blovalue.getValue());
    	imagingObservation2.setCodeValue(blovalue.getKey());
    	imagingObservation2.setCodingSchemeDesignator("VASARI");
    	imagingObservation2.setId(BigInteger.ZERO);
    	imagingObservationList.add(imagingObservation2);
    	
    	ImagingObservation.ImagingObservationCharacteristicCollection imagingObservationCharacteristicCollection = 
    		factory.createImagingObservationImagingObservationCharacteristicCollection();
    	imagingObservation1.setImagingObservationCharacteristicCollection(imagingObservationCharacteristicCollection);
    	List<ImagingObservationCharacteristic> imagingObservationCharacteristicList = imagingObservationCharacteristicCollection.getImagingObservationCharacteristic();
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Ependymal Invasion",vasariContainer.get(2), jLesioncombo01, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Definition of the Non-Enhancing Margin",vasariContainer.get(3), jLesioncombo02, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("T1/FLAIR Ratio",vasariContainer.get(4), jLesioncombo03, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Proportion Enhancing",vasariContainer.get(5), jLesioncombo04, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Side of Tumor Epicenter",vasariContainer.get(6), jLesioncombo05, factory));
    	//imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Thickness of the Enhancing Margin",vasariContainer.get(6), jLesioncombo06, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("nCET Tumor Crosses Midline",vasariContainer.get(7), jLesioncombo11, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Enhancing Tumor Crosses Midline",vasariContainer.get(8), jLesioncombo12, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Proportion of Edema",vasariContainer.get(9), jLesioncombo13, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Proportion Necrosis",vasariContainer.get(10), jLesioncombo14, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Proportion nCET",vasariContainer.get(11), jLesioncombo15, factory));
    	//imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Calvarial Remodeling",vasariContainer.get(12), jLesioncombo16, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Definition of the Enhancing Margin",vasariContainer.get(12), jLesioncombo21, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Thickness of the Enhancing Margin",vasariContainer.get(13), jLesioncombo22, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Multifocal or Multicentric",vasariContainer.get(14), jLesioncombo23, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Enhancement Quality",vasariContainer.get(15), jLesioncombo24, factory));
    	imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Deep WM Invasion",vasariContainer.get(16), jLesioncombo25, factory));
    	//imagingObservationCharacteristicList.add(getimagingObservationCharacteristic("Hemorrhage",vasariContainer.get(18), jLesioncombo26, factory));

    	return imagingObservationCollection;
    }
    
    private ImagingObservationCharacteristic getimagingObservationCharacteristic(String codeMeaning, ArrayList<valuepair> list, JComboBox jCombo, ObjectFactory factory){
    	if(0 == jCombo.getSelectedIndex()){
    		return null;
    	}
    	ImagingObservationCharacteristic imagingObservationCharacteristic = factory.createImagingObservationCharacteristic();
    	valuepair val = list.get(jCombo.getSelectedIndex());
    	imagingObservationCharacteristic.setCodeMeaning(val.getValue());
    	imagingObservationCharacteristic.setCodeValue(val.getKey());
    	imagingObservationCharacteristic.setCodingSchemeDesignator("VASARI");
    	imagingObservationCharacteristic.setId(BigInteger.ZERO);
    	return imagingObservationCharacteristic;
    }
    
	public JButton getPanZoomBtn(){
		return jBtnPanZoom;
	}
	public JButton getRotateBtn(){
		return jBtnRotate;
	}
	public JButton getResetBtn(){
		return jBtnReset;
	}
	public JButton getPolygonBtn(){
		return jBtnPolygon;
	}
	public JButton getContourBtn(){
		return jBtnContour;
	}
	public JButton getTransformBtn(){
		return jBtnTransform;
	}
	public JButton getModifyBtn(){
		return jBtnModify;
	}
	public JButton getUndoBtn(){
		return jBtnUndo;
	}
	public JButton getClearBtn(){
		return jBtnClear;
	}
	public JButton getLoadBtn(){
		return jBtnLoad;
	}
	public JButton getSaveBtn(){
		return jBtnSave;
	}
	public JButton getDoneBtn(){
		return jBtnDone;
	}
	public JRadioButton getRadioBtn0(){
		return jRadioButton00;
	}
	public JRadioButton getRadioBtn1(){
		return jRadioButton01;
	}
	public String getAnnotationName(){
		return jAnnoTextField.getText();
	}
	
	public void setAnnotationName(String name){
		System.out.println(name);

		jAnnoTextField.setText(name);
	}
	public void setAnnotationType(String type){
		System.out.println(type);
		
		if (type.compareTo("Brain tumor baseline target lesion") == 0)
			jRadioButton00.setSelected(true);
		else if (type.compareTo("Brain tumor follow-up target lesion") == 0)
			jRadioButton01.setSelected(true);

	}
	public void setImageCharacteristics(String key, String value){
		System.out.println(key + value);
		
		for ( int i = 0; i < vasariContainer.size(); i++ ){
			ArrayList<valuepair> content = vasariContainer.get(i);
			
			for ( int j = 0; j < content.size(); j++ ){
				String codeKey = content.get(j).getKey();
				if (codeKey.compareTo(key) == 0) {
					attributes.get(i).setSelectedItem(value);
					return;
				}
			}			
		}			
	}
	public void resetContents(){
		jAnnoTextField.setText("Annotation1");
		jRadioButton00.setSelected(false);
		jRadioButton01.setSelected(false);
		
		for (int i = 0; i < attributes.size(); i++){
			attributes.get(i).setSelectedItem("<select from list>");
		}		
	}
}

class valuepair{
	public valuepair(String keystr, String valuestr){
		key = keystr;
		value = valuestr;
	}
	public valuepair(){
		
	}
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
