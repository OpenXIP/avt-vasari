
/*
Copyright (c) 2010, Washington University in St. Louis. 
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.nema.dicom.wg23.Rectangle;

/**
 * <font  face="Tahoma" size="2">
 * <br></br>
 * @version	May 2009
 * @author Lawrence Tarbox
 * </font>
 * This file is derived from ApplicationFrameTempl.java in the 
 * edu.wustl.xipApplication.samples directory, coupled with
 * sections extracted out of rev. 216 on trunk of ImageAnnotation.java
 */
public class ImageAnnotationFrame extends JFrame {		
	private static final long serialVersionUID = 1L;	
	ImageAnnotationPanel appPanel = new ImageAnnotationPanel();
	ImageAnnotation mainApp;
	
	public ImageAnnotationFrame (ImageAnnotation mainAppIn){
		mainApp = mainAppIn;

		setUndecorated(true);

		/*Set application dimensions */
		Rectangle rect = mainApp.getClientToHost().getAvailableScreen(null);			
		setBounds(rect.getRefPointX(), rect.getRefPointY(), rect.getWidth(), rect.getHeight());
				
		
		// previously in ImageAnnotation
		appPanel.setVisible(false);
		appPanel.addOutputAvailableListener(mainApp);

		setContentPane(appPanel);

		setVisible(true);
	}
	
	public Dimension getAppPanelDimension(){
		return getPreferredSize();
	}
	
	public void setAppPanelDimension(Dimension size){
		setPreferredSize(size);
	}			
	
	public JPanel getDisplayPanel(){
		return appPanel;
	}
	
	public void setSceneGraphInputs(String inputs)
	{
		String outDir = mainApp.getClientToHost().getOutputDir();			
		appPanel.setOutputDir(outDir);

		appPanel.getIvCanvas().set("VR_Text.whichChild", "0");
		
		//update scene graph
		if(appPanel.getIvCanvas().set("LoadDicom.name", inputs)){
			System.out.println("Load Dicom files Successfully");
			appPanel.getIvCanvas().processQueue();
		}

		appPanel.setVisible(true);
		appPanel.repaint();
	}

	public void setOutputDir(String file) {
		appPanel.setOutputDir(file);
		
	}

	public void loadAIMFile(String aimFile) {
		File aim = new File(aimFile);
//		File aim = new File("D:\\Vasari_TCGA.xml");
		appPanel.loadAIMFile(aim);		
	}
	
	public void emptyImageData(){
		appPanel.emptyImageData();
	}
	
	public void setImageData(String strUID, String strFile){
		appPanel.setImageData(strUID, strFile);
	}
}
