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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.nema.dicom.wg23.ArrayOfObjectLocator;
import org.nema.dicom.wg23.ObjectLocator;
import org.nema.dicom.wg23.State;
import org.nema.dicom.wg23.Uuid;

import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.AttributeTag;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.dicom.SequenceAttribute;
import com.pixelmed.dicom.SequenceItem;
import com.pixelmed.dicom.UIDGenerator;

import com.siemens.cmiv.avt.vasari.AnnotationPanel;

import edu.wustl.xipApplication.application.ApplicationTerminator;
import edu.wustl.xipApplication.recist.recistGUI.UnderDevelopmentDialog;
import edu.wustl.xipApplication.wg23.OutputAvailableEvent;
import edu.wustl.xipApplication.wg23.OutputAvailableListener;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.AnatomicEntity;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.DICOMImageReference;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.GeometricShape;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Image;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotationIdentifier;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageReference;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservation;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservationCharacteristic;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ObjectFactory;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ProbabilityMap;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Series;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.SpatialCoordinate;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Study;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ThreeDimensionSpatialCoordinate;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.TwoDimensionSpatialCoordinate;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation.AnatomicEntityCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.Annotation.ImagingObservationCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.GeometricShape.SpatialCoordinateCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotation.GeometricShapeCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImageAnnotation.ImageReferenceCollection;
import gme.cacore_cacore._3_2.edu_northwestern_radiology.ImagingObservation.ImagingObservationCharacteristicCollection;

/**
 * @author Jie Zheng
 *
 */


public class ImageAnnotationPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4680059836530067596L;

	final int WIDTH = 350;
		
	ivCanvas mivCanvas;
//	ivExtCanvas mivCanvas;
    AnnotationPanel annotationPanel = new AnnotationPanel(); 
    String	outDir = "";
    String	annotatorID = "";
    String  annotationType = "Brain tumor baseline target lesion";
    String  annotationID = "IPAD4";
    boolean bAIMLoad = false; 
    
    Map<String, String> image_data = new HashMap<String, String>();
        
    JFileChooser fc;
    
    public ImageAnnotationPanel() { 
    	setLayout(null);
    	
		mivCanvas = new ivCanvas();
//		mivCanvas = new ivExtCanvas();
		add(mivCanvas);
		add(annotationPanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		mivCanvas.setBounds(0, 0, screenSize.width - WIDTH, screenSize.height);
		annotationPanel.setBounds(screenSize.width - WIDTH, 0, WIDTH, screenSize.height);
		
		mivCanvas.initialize();
		loadLibrary();		
	    runAnnotation();
	    
	    annotationPanel.getPanZoomBtn().addActionListener(this);
	    annotationPanel.getRotateBtn().addActionListener(this);
	    annotationPanel.getResetBtn().addActionListener(this);
	    
	    annotationPanel.getPolygonBtn().addActionListener(this);
	    annotationPanel.getContourBtn().addActionListener(this);
	    annotationPanel.getTransformBtn().addActionListener(this);

	    annotationPanel.getModifyBtn().addActionListener(this);
	    annotationPanel.getUndoBtn().addActionListener(this);
	    annotationPanel.getClearBtn().addActionListener(this);
	    
	    annotationPanel.getSaveBtn().addActionListener(this);
//	    annotationPanel.getDoneBtn().addActionListener(this);
	    
	    annotationPanel.getRadioBtn0().addActionListener(this);
	    annotationPanel.getRadioBtn1().addActionListener(this);

	    annotationPanel.getLoadBtn().addActionListener(this);
	    
	    Random rand = new Random();
	    annotatorID = Integer.toString(rand.nextInt());
	    
	    //Create a file chooser
        fc = new JFileChooser();
        
        //internal testing to load all the files
//        setupImageData("D:\\Projects\\AVT\\01Data\\TCGA-08-0244\\1.3.6.1.4.1.9328.50.46.130563880911723253267280582465817207504\\1.3.6.1.4.1.9328.50.46.236326650903196607542589296789154905463");
    }
    
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ImageAnnotationPanel panel = new ImageAnnotationPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);	
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		frame.setBounds(0, 0, screenSize.width, screenSize.height);

	}
   
	public void loadLibrary() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./ivJava.ini"));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					int index = line.indexOf("LoadLibrary=");
					if (index >= 0) {
						String Library = line.substring(index + 12);
						Library = Library.replace(';', ',');
						System.out.println("Loading rad extensions : ");
						System.out.println(Library);
						if (!mivCanvas.loadLibraries(Library))
							System.out.println("Not all rad extensions could be loaded");
						break;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void runAnnotation(){	   
		File ivFile = new File("vasari.iv");
	    String filePth;
	    if(ivFile.exists()) {
	    	filePth = ivFile.getAbsolutePath();
	    } else {
	    	return;
	    }	              	          
		if (null != mivCanvas && filePth.length() != 0) {
			try {
				mivCanvas.loadGraphOpenGL(filePth);
				mivCanvas.repaint();	    
			} catch (Exception e) {
				  e.printStackTrace();
			}
		}
	}
	
	public ivCanvas getIvCanvas(){
		return mivCanvas;
	}
	   
	public AnnotationPanel getAnnotationPanel(){
		return annotationPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == annotationPanel.getPanZoomBtn()){
			getIvCanvas().set("Axial_MprExaminer.mode", "PANZOOM");

			annotationPanel.setinforLabel("View image - pan/zoom");
			System.out.println("pan/zoom");
			
//			File aim = new File("d:\\Vasari_TCGA.xml");
//			loadAIMFile(aim);
		}
		
		if(e.getSource() == annotationPanel.getRotateBtn()){
			getIvCanvas().set("Axial_MprExaminer.mode", "ROTATE_NORMAL");
			getIvCanvas().set("GetPoints.search", "");
			
			annotationPanel.setinforLabel("View image - rotate");
			System.out.println("rotate in plane");
		}

		if(e.getSource() == annotationPanel.getResetBtn()){
			getIvCanvas().set("Axial_MprExaminer.viewOrientation", "");
			getIvCanvas().set("Axial_MprExaminer.viewAll", "");
			getIvCanvas().set("GetPoints.search", "");
			
			annotationPanel.setinforLabel("View image - reset");
			System.out.println("reset");
		}
		
		if(e.getSource() == annotationPanel.getPolygonBtn()){
			if (bAIMLoad){				
				annotationPanel.resetContents();
				getIvCanvas().set("Overlay_Load.clear", "");
				getIvCanvas().set("Load_Create_Shape.index", "0");

				bAIMLoad = false;
			}
			
			getIvCanvas().set("Axial_MprExaminer.mode", "NONE");
			getIvCanvas().set("Overlay_Switch.whichChild", "0");
			getIvCanvas().set("Overlay_Translation.on", "FALSE");
			getIvCanvas().set("Overlay_Transform.on", "FALSE");
			getIvCanvas().set("Overlay_Shape.clear", "");
			getIvCanvas().set("Overlay_Shape.shapeClassName", "SoXipPolygon");
			getIvCanvas().set("Overlay_Shape.create", "TRUE");
			
			annotationPanel.setinforLabel("Define ROI - polygon");
			System.out.println("ROI - polygon");
		}

		if(e.getSource() == annotationPanel.getContourBtn()){
			if (bAIMLoad){				
				annotationPanel.resetContents();
				getIvCanvas().set("Overlay_Load.clear", "");
				getIvCanvas().set("Load_Create_Shape.index", "0");
				
				bAIMLoad = false;
			}
			
			getIvCanvas().set("Axial_MprExaminer.mode", "NONE");
			getIvCanvas().set("Overlay_Switch.whichChild", "0");
			getIvCanvas().set("Overlay_Translation.on", "FALSE");
			getIvCanvas().set("Overlay_Transform.on", "FALSE");
			getIvCanvas().set("Overlay_Shape.clear", "");
			getIvCanvas().set("Overlay_Shape.shapeClassName", "SoXipContour");
			getIvCanvas().set("Overlay_Shape.create", "TRUE");
			
			annotationPanel.setinforLabel("Define ROI - contour");
			System.out.println("ROI - contour");
		}

		if(e.getSource() == annotationPanel.getClearBtn()){
			getIvCanvas().set("Overlay_Shape.clear", "");
			getIvCanvas().set("Overlay_Shape.create", "FALSE");
			
			annotationPanel.setinforLabel("Define ROI - clear");
			System.out.println("ROI - clear");
		}
		
		if(e.getSource() == annotationPanel.getTransformBtn()){
			getIvCanvas().set("Axial_MprExaminer.mode", "NONE");
			getIvCanvas().set("Cut.on", "FALSE");
			getIvCanvas().set("Overlay_Translation.on", "TRUE");
			getIvCanvas().set("Overlay_Transform.on", "TRUE");
			
			annotationPanel.setinforLabel("Define ROI - transform");
			System.out.println("ROI - transform");
		}

		if(e.getSource() == annotationPanel.getModifyBtn()){
			getIvCanvas().set("Axial_MprExaminer.mode", "NONE");
			getIvCanvas().set("Overlay_Translation.on", "FALSE");
			getIvCanvas().set("Overlay_Transform.on", "FALSE");
			getIvCanvas().set("Cut.on", "TRUE");

			annotationPanel.setinforLabel("Define ROI - modify");
			System.out.println("ROI - modify");
		}
		
		if(e.getSource() == annotationPanel.getUndoBtn()){
			getIvCanvas().set("Cut.undo", "");
			
			annotationPanel.setinforLabel("Define ROI - undo");
			System.out.println("ROI - undo");
		}

		if(e.getSource() == annotationPanel.getSaveBtn()){
			if (annotationPanel.checkSelectItems()){
				
				URI uri = null;
				File aimFile = null;
			
				if (!outDir.isEmpty()){
					try {
						uri = new URI(outDir);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					File file = new File(uri);
					fc.setCurrentDirectory(file);
					fc.setSelectedFile(new File(file.toString(), "Vasari_TCGA.xml"));
					int returnVal = fc.showSaveDialog(this);
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		            	aimFile = fc.getSelectedFile();
		                //This is where a real application would save the file.
						System.out.println(aimFile.getPath());
		            } 
		            else{
						try {
							aimFile = File.createTempFile("Vasari_TCGA", ".xml", file);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						};
						System.out.println(aimFile.getPath());
		            }
				}
				else
					aimFile = getFileName();	            	
				
				try {
					marshall(aimFile);

					if (!outDir.isEmpty()){
						List<File> serializedAIMs = new ArrayList<File>();
						serializedAIMs.add(aimFile);
	
						notifyDataAvailable(serializedAIMs);	
					}
					
					annotationPanel.setinforLabel("Save annotation result");
					System.out.println("Finished annotation");
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DicomException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			System.out.println("ROI - save");
		}
		
		if(e.getSource() == annotationPanel.getDoneBtn()){
			new UnderDevelopmentDialog(annotationPanel.getDoneBtn().getLocationOnScreen());
			return;
			
//			int n = JOptionPane.showConfirmDialog(
//				    this, "Would you like to save the last change and exit the application?",
//				    "Warning",
//				    JOptionPane.YES_NO_OPTION);
//			if (n == JOptionPane.YES_OPTION){
//				annotationPanel.setinforLabel("Save the update and exit");
//				System.out.println("Vasari - exit");
				
//				getClientToHost().notifyStateChanged(State.EXIT);						
				//terminating endpoint and existing system is accomplished through ApplicationTerminator
				//and ApplicationScheduler. ApplicationSechduler is present to alow termination delay if needed (posible future use)
//				ApplicationTerminator terminator = new ApplicationTerminator(getEndPoint());
//				Thread t = new Thread(terminator);
//				t.start();	
//			}
		}
		
		if(e.getSource() == annotationPanel.getRadioBtn0()){
			annotationType = "Brain tumor baseline target lesion";
			annotationID = "IPAD4";
		}
		
		if(e.getSource() == annotationPanel.getRadioBtn1()){
			annotationType = "Brain tumor follow-up target lesion";
			annotationID = "IPAD5";
		}
		
		//For internal testing
		if(e.getSource() == annotationPanel.getLoadBtn()){
			FileFilter type = new ExtensionFilter("XML file", ".xml");	
			fc.addChoosableFileFilter(type);
			int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	File aimFile = fc.getSelectedFile();
                //This is where a real application would load the file.
            	
            	loadAIMFile(aimFile);
				annotationPanel.setinforLabel("Load annotation result");
				System.out.println(aimFile.getPath());
            } 
		}
	}
	
	OutputAvailableListener listener; 
	public void addOutputAvailableListener(OutputAvailableListener l){
		 this.listener = l;
	}
		
	void notifyDataAvailable(List<File> serializedAIMs){
		OutputAvailableEvent event = new OutputAvailableEvent(serializedAIMs);
		listener.outputAvailable(event);
	}
	
	public void marshall(File xmlFile) throws JAXBException, DicomException{
		ObjectFactory factory = new ObjectFactory(); 
		
		//create ImageAnnotation
		ImageAnnotation annot = factory.createImageAnnotation();
		annot.setId(BigInteger.ZERO);
		annot.setAimVersion("TCGA");
		annot.setDateTime(getCurrentTime());
		annot.setName(annotationPanel.getAnnotationName());
		annot.setCodingSchemeDesignator("IPAD");
		annot.setCodingSchemeVersion("v1_rv2");
		annot.setCodeValue(annotationID);
		annot.setCodeMeaning(annotationType);
		
		UIDGenerator uid = new UIDGenerator();
		annot.setUniqueIdentifier(uid.getNewUID());

		//create user
		gme.cacore_cacore._3_2.edu_northwestern_radiology.User user = factory.createUser();
		user.setName("wustl");
		user.setId(new BigInteger("0"));
		user.setLoginName("wustl");
		user.setRoleInTrial("annotation");
		Annotation.User user2 = factory.createAnnotationUser();
		user2.setUser(user);
		annot.setUser(user2);					

		//create AnatomicEntity
		annot.setAnatomicEntityCollection(annotationPanel.assmbleAnatomicEntity());
		
		//create imagingObservation
		annot.setImagingObservationCollection(annotationPanel.assmbleImageObservations());
		
		//create imageReference collection
		DICOMImageReference imageRef = factory.createDICOMImageReference();
		imageRef.setId(BigInteger.ZERO);
		
		//study
		DICOMImageReference.Study study = factory.createDICOMImageReferenceStudy();
		
		gme.cacore_cacore._3_2.edu_northwestern_radiology.Study normalStudy = factory.createStudy();
		normalStudy.setId(BigInteger.ZERO);

		//study 
//			normalStudy.setDate(datetimeStu);
		normalStudy.setInstanceUID(parseOpenInventorString(getIvCanvas().get("Study_UID.string")));
		
		//series
		gme.cacore_cacore._3_2.edu_northwestern_radiology.Series seriesItem = factory.createSeries();
		
		//series Number
		seriesItem.setId(BigInteger.ZERO);
		
		//Modality
//			seriesItem.setModality("MR");
		
		//series instance UID (as the FrameofReferenceUID)
		seriesItem.setInstanceUID(parseOpenInventorString(getIvCanvas().get("Series_UID.string")));
		
		gme.cacore_cacore._3_2.edu_northwestern_radiology.Series.ImageCollection imageColl = factory.createSeriesImageCollection();

		List<Image> imageList = imageColl.getImage();
						
		Image image = factory.createImage();
		image.setId(BigInteger.ZERO);
		image.setSopClassUID(parseOpenInventorString(getIvCanvas().get("sopClass_UID.string")));
		image.setSopInstanceUID(parseOpenInventorString(getIvCanvas().get("sopInstance_UID.string")));
		
		imageList.add(image);
		
		seriesItem.setImageCollection(imageColl);
		
		gme.cacore_cacore._3_2.edu_northwestern_radiology.Study.Series series = factory.createStudySeries();
		series.setSeries(seriesItem);
		
		normalStudy.setSeries(series);
		study.setStudy(normalStudy);
		
		imageRef.setStudy(study);
		
		ImageReferenceCollection imagingReferenceColl = factory.createImageAnnotationImageReferenceCollection();
		List<ImageReference> imageReferenceList = imagingReferenceColl.getImageReference();
		imageReferenceList.add(imageRef);
		annot.setImageReferenceCollection(imagingReferenceColl);

		//Create patient
		gme.cacore_cacore._3_2.edu_northwestern_radiology.Patient pat = factory.createPatient();
		
		pat.setId(BigInteger.ZERO);
		
		//PatientName
		pat.setName(parseOpenInventorString(getIvCanvas().get("Patient_Name.string")));
		
		//PatientID
		pat.setPatientID(parseOpenInventorString(getIvCanvas().get("Patient_ID.string")));

		//PatientSex
		pat.setSex(parseOpenInventorString(getIvCanvas().get("Patient_Gender.string")));
		
		ImageAnnotation.Patient _pat = factory.createImageAnnotationPatient();
		_pat.setPatient(pat);
		annot.setPatient(_pat);

		String strUID = parseOpenInventorString(getIvCanvas().get("sopInstance_UID.string"));
		
		List<String> imgInstance = new ArrayList<String>();
		List<Float> imagePos = new ArrayList<Float>();
		List<Float> pixelSpacing = new ArrayList<Float>();
		List<Integer> imageSize = new ArrayList<Integer>();
		if (!getImageInformation(strUID, imgInstance, imagePos, imageSize, pixelSpacing)){
			System.out.println("Store AIM object failed - can not get dicom information");
			return;
		}
		
		if (imgInstance.size() < 1 || imagePos.size() < 3 || pixelSpacing.size() < 2){
			System.out.println("Store AIM object failed - error in getting dicom information");
			return;
		}
			
		annot.setGeometricShapeCollection(assmbleGeometricShapeCollection(strUID, imgInstance, imagePos, imageSize, pixelSpacing));
		
       try{
    	   JAXBContext jaxbContext = JAXBContext.newInstance("gme.cacore_cacore._3_2.edu_northwestern_radiology");
    	   Marshaller marshaller = jaxbContext.createMarshaller();
           marshaller.setProperty("jaxb.schemaLocation", "gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM AIM_TCGA09302009_XML.xsd");
           JAXBElement<ImageAnnotation> catalogElement = factory.createImageAnnotation(annot);
           marshaller.marshal(catalogElement,new FileOutputStream(xmlFile));
        } catch (FileNotFoundException e){
           e.printStackTrace();
        } catch (JAXBException e){
           e.printStackTrace();
	    }
	}

	void setOutputDir(String outDir)
	{
		this.outDir = outDir;
		
		System.out.println(outDir);
	}

	ArrayOfObjectLocator createDataAsFile(List<File> serializedFiles) {
		ArrayOfObjectLocator arrayObjLoc = new ArrayOfObjectLocator();
		List<ObjectLocator> listObjectLocs = arrayObjLoc.getObjectLocator();

		for (int i = 0; i < serializedFiles.size(); i++){
		Uuid objDescUUID = new Uuid();
		objDescUUID.setUuid(UUID.randomUUID().toString());

		ObjectLocator objLoc = new ObjectLocator();				
		objLoc.setUuid(objDescUUID);				
		try {
			objLoc.setUri(serializedFiles.get(i).toURI().toURL().toExternalForm());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		listObjectLocs.add(objLoc);
	}
		
		return arrayObjLoc;
	}
	
    private XMLGregorianCalendar getCurrentTime(){
    	DatatypeFactory factory = null;
    	try{
    	    factory = DatatypeFactory.newInstance();
    	    
    	}catch (DatatypeConfigurationException e){
    		e.printStackTrace();
    	}
    	XMLGregorianCalendar xmlcalender = factory.newXMLGregorianCalendar();
	    Calendar calendar = Calendar.getInstance();
	    xmlcalender.setYear(calendar.get(Calendar.YEAR));
	    xmlcalender.setMonth(calendar.get(Calendar.MONTH) +1);
	    xmlcalender.setDay(calendar.get(Calendar.DAY_OF_MONTH));
	    xmlcalender.setHour(calendar.get(Calendar.HOUR_OF_DAY));
	    xmlcalender.setMinute(calendar.get(Calendar.MINUTE));
	    xmlcalender.setSecond(calendar.get(Calendar.SECOND));
	    return xmlcalender;
    }
    
	private File getFileName(){
		FileSystemView fileSystemView =FileSystemView.getFileSystemView(); 
		File f = fileSystemView.getDefaultDirectory(); 
		String filename = f.toString() + "\\vasari_TCGA.xml";
		File file = new File(filename);
		if(file.exists()){
			file.delete();
		}
		return file;
	}

    public GeometricShapeCollection assmbleGeometricShapeCollection(String strUID, List<String> instance, List<Float> position, List<Integer> size, List<Float> spacing){
		ObjectFactory factory = new ObjectFactory(); 
		
		GeometricShapeCollection shapecollection = factory.createImageAnnotationGeometricShapeCollection();
		List<GeometricShape> shapes = shapecollection.getGeometricShape();
		
		GeometricShape shape = factory.createMultiPoint();
		shape.setId(BigInteger.ZERO);
		shape.setShapeIdentifier(new BigInteger("1"));
		shape.setIncludeFlag(true);
		shape.setLineColor("YELLOW");
		shape.setLineOpacity("OPACITY");
		shape.setLineStyle("SOLID");

		SpatialCoordinateCollection coordinateCollection = factory.createGeometricShapeSpatialCoordinateCollection();
		List<SpatialCoordinate> pointList = coordinateCollection.getSpatialCoordinate();
		
		String points = parseOpenInventorString(getIvCanvas().get("Shape_Points.point"));
		ArrayList<Point3D> pointlist = parseListString(points);
		
		for (int i = 0; i < pointlist.size(); i++){
			TwoDimensionSpatialCoordinate point = factory.createTwoDimensionSpatialCoordinate();
			point.setId(new BigInteger(Integer.toString(i)));
			// TODO:  properly transform the 3D coordinate in the scene graph back to 2D pixel coordinates.
			float fx = pointlist.get(i).getX() * size.get(0);
			float fy = pointlist.get(i).getY() * size.get(1);
			point.setX(fx);
			point.setY(fy);
			point.setCoordinateIndex(new BigInteger(Integer.toString(i)));
			point.setImageReferenceUID(strUID);
			// TODO:  Find the frame number within the DICOM object e.g. from the examiner, 
			// to properly handle the multi-frame case.  
			// This code only works for single frame objects.
			point.setReferencedFrameNumber(new BigInteger(instance.get(0)));
			
			pointList.add(point);
		}		
		
		shape.setSpatialCoordinateCollection(coordinateCollection);
		shapes.add(shape);
		
		return shapecollection;
    }
    
    public ArrayList<Point3D> parseListString(String points)
    {
    	ArrayList<Point3D> list = new ArrayList<Point3D>();
    	
    	String tmpString = points.replace('[', ' ');
    	tmpString = tmpString.replace(']', ' ');
    	String buffer = tmpString.trim();
    	
		String[] tokens = buffer.split(",");
		for(String s:tokens){
			Point3D pt = new Point3D(s);
			list.add(pt);
		}
		
		return list;
    }
    
    //utility function to remove the extra quotes
    public String parseOpenInventorString(String str)
    {
    	String  strBuf = str;
    	
    	int index0 = str.indexOf('"');
    	int index1 = str.lastIndexOf('"');
    	
    	if (index0 >= 0 && index1 < str.length())
    		strBuf = str.substring(index0+1, index1);    	
    	
    	return strBuf;
    }
    
    public boolean loadAIMFile(File aimFile){
     	if (!aimFile.exists())
    		return false;
    	
        try{
		   JAXBContext jaxbContext = JAXBContext.newInstance("gme.cacore_cacore._3_2.edu_northwestern_radiology");
		   Unmarshaller u = jaxbContext.createUnmarshaller();
		   JAXBElement obj = (JAXBElement)u.unmarshal(aimFile);			
		   ImageAnnotation imageAnnotation = ((ImageAnnotation)obj.getValue());
		   
		   String annotationName = imageAnnotation.getName();
		   annotationPanel.setAnnotationName(annotationName);
		   
		   String annotationType = imageAnnotation.getCodeMeaning();
		   annotationPanel.setAnnotationType(annotationType);
		   
		   String regionCode = imageAnnotation.getAnatomicEntityCollection().getAnatomicEntity().get(0).getCodeValue();
		   String annotationRegion = imageAnnotation.getAnatomicEntityCollection().getAnatomicEntity().get(0).getCodeMeaning();
		   annotationPanel.setImageCharacteristics(regionCode, annotationRegion);
		   
		   Map<String, String> list = new HashMap<String, String>();
		   List<ImagingObservation> imagingObservationColl = imageAnnotation.getImagingObservationCollection().getImagingObservation();
		   for(int i = 0; i < imagingObservationColl.size(); i++){
			   ImagingObservation imagingObservation = imagingObservationColl.get(i);
			   String str = imagingObservation.getCodeValue();
			   
			   if(str.compareTo("84") == 0){ //Tumor
				   List<ImagingObservationCharacteristic> imagingObservationCharacteristicList = 
					   imagingObservation.getImagingObservationCharacteristicCollection().getImagingObservationCharacteristic();
				   
				   for (int j = 0; j < imagingObservationCharacteristicList.size(); j++){
					   String codeValue = imagingObservationCharacteristicList.get(j).getCodeValue();
					   String codeStr =  imagingObservationCharacteristicList.get(j).getCodeMeaning();
					   
					   list.put(codeValue, codeStr);
					   annotationPanel.setImageCharacteristics(codeValue, codeStr);
				   }
			   }
			   else{ // Eloquent Brain Involvement
				   String imgObservation = imagingObservation.getCodeMeaning();
				   annotationPanel.setImageCharacteristics(str, imgObservation);
			   }
			}
		  
		   ImageReference imageReference = imageAnnotation.getImageReferenceCollection().getImageReference().get(0);
		   DICOMImageReference ref = (DICOMImageReference) imageReference;
		   Study study = ref.getStudy().getStudy();	 
		   Series series = study.getSeries().getSeries();
		   Image image = series.getImageCollection().getImage().get(0);
		   
		   String refSOPInstanceUID = image.getSopInstanceUID();
		   List<String> imgInstance = new ArrayList<String>();
		   List<Float> imagePos = new ArrayList<Float>();
		   List<Float> pixelSpacing = new ArrayList<Float>();
		   List<Integer> imageSize = new ArrayList<Integer>();
		   if (!getImageInformation(refSOPInstanceUID, imgInstance, imagePos, imageSize, pixelSpacing)){
				System.out.println("Load AIM object failed - can not get dicom information");
				return false;
		   }
		   
		   if (imgInstance.size() < 1 || imagePos.size() < 3 || pixelSpacing.size() < 2){
				System.out.println("Load AIM object failed - error in getting dicom information");
				return false;
		   }
		   		   
		   GeometricShape shape = imageAnnotation.getGeometricShapeCollection().getGeometricShape().get(0);
		   String shapeType = shape.toString();
		   
		   Map <Integer, String> indexedPoint = new HashMap<Integer, String>();
		   
		   List<SpatialCoordinate> pointList = shape.getSpatialCoordinateCollection().getSpatialCoordinate();
		   
		   for (int i = 0; i < pointList.size(); i++){
			   Class<? extends SpatialCoordinate> pt = pointList.get(i).getClass();
			   if (pt.getName().equals("ThreeDimensionSpatialCoordinate")) {
				   ThreeDimensionSpatialCoordinate point = (ThreeDimensionSpatialCoordinate) pointList.get(i);
				   DecimalFormat ptFormat = new DecimalFormat("0.000");
	
				   String str = ptFormat.format(point.getX());
				   str += " " + ptFormat.format(point.getY());
				   str += " " + ptFormat.format(point.getZ());
				   str += ",";
				   
				   BigInteger index = point.getCoordinateIndex();
				   indexedPoint.put(index.intValue(), str);
			   }
			   else {
				   TwoDimensionSpatialCoordinate point = (TwoDimensionSpatialCoordinate) pointList.get(i);
				   DecimalFormat ptFormat = new DecimalFormat("0.000");
	
				   String str = ptFormat.format(point.getX() / imageSize.get(0));
				   str += " " + ptFormat.format(point.getY() / imageSize.get(1));
				   // TODO: Find the z in the image position Attribute in the DICOM header
				   str += " " + ptFormat.format(imagePos.get(2)); 
				   str += ",";
				   
				   BigInteger index = point.getCoordinateIndex();
				   indexedPoint.put(index.intValue(), str);
			   }
		   }
		   
		   String pointBuffer = "[ ";
		   for (int i = 0; i < indexedPoint.size(); i++){
			   	if (indexedPoint.containsKey(i)){
			   		pointBuffer += indexedPoint.get(i);
			   	}
		   }
		   pointBuffer += "]";
		   
		   getIvCanvas().set("Overlay_Switch.whichChild", "0");
		   getIvCanvas().set("Overlay_Shape.clear", "");
		   getIvCanvas().set("Set_Points.contour", pointBuffer);
		   getIvCanvas().set("Load_Create_Shape.index", "1");
		   getIvCanvas().set("Overlay_Load.create", "");
		   getIvCanvas().set("Slice_Update.trigger", "");
		   
		   bAIMLoad = true;
		   
        } catch (JAXBException e){
            e.printStackTrace();
 	    }    	
    	
    	return false;
    }
    
    void emptyImageData(){
    	image_data.clear();
    }
    
    void setImageData(String strUID, String strFile){
    	image_data.put(strUID, strFile);
    }
    
    String getImageData(String strUID){
    	if (image_data.containsKey(strUID))
    		return image_data.get(strUID);
    	
    	return "";
    }
    
    //get dicom image position information
    boolean getImageInformation(String strUID, List<String> img_Instance, List<Float> img_Position, List<Integer> img_Size, List<Float> img_Spacing){
    	String input = getImageData(strUID);
    	
    	if (input.isEmpty())
    		return false;
    	
		File dcmFile = new File(input);
		DicomInputStream segInput;
	
		try {
			segInput = new DicomInputStream(dcmFile);
			
			AttributeList tags = new AttributeList();
			tags.read(segInput);
			
			//image instance number
			AttributeTag tag = new AttributeTag(0x20, 0x13);
			Attribute attrib = tags.get(tag);
			if (attrib != null)
				img_Instance.add(attrib.getSingleStringValueOrEmptyString());
			
			//image position
			tag = new AttributeTag(0x20, 0x32);
			attrib = tags.get(tag);
			if (attrib != null){
				float[]	position = attrib.getFloatValues();		
				for (int i = 0; i < position.length; i++){
					img_Position.add(position[i]);
				}				
			}
				
			//pixel spacing
			tag = new AttributeTag(0x28, 0x30);
			attrib = tags.get(tag);
			if (attrib != null){
				float[] spacing = attrib.getFloatValues();
				for (int i = 0; i < spacing.length; i++){
					img_Spacing.add(spacing[i]);
				}
			}
			
			//image column
			tag = new AttributeTag(0x28, 0x11);
			attrib = tags.get(tag);
			if (attrib != null){
				int nColumn = attrib.getSingleIntegerValueOrDefault(0);
				if (nColumn != 0)
					img_Size.add(nColumn);
			}
			
			//image row
			tag = new AttributeTag(0x28, 0x10);
			attrib = tags.get(tag);
			if (attrib != null){
				int nRow = attrib.getSingleIntegerValueOrDefault(0);
				if (nRow != 0)
					img_Size.add(nRow);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DicomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
    }
    
	public void setupImageData(String folder){
		emptyImageData();
		
		File dir = new File(folder);
		
		String[] children = dir.list();
		if (children != null){		
			for (int i = 0; i < children.length; i++){
				String input = folder + "\\" + children[i];

				File dcmFile = new File(input);
				DicomInputStream segInput;
	
				try {
					segInput = new DicomInputStream(dcmFile);
					
					AttributeList tags = new AttributeList();
					tags.read(segInput);
					
					AttributeTag tag = new AttributeTag(0x8, 0x18);
					Attribute attrib = tags.get(tag);
					if (attrib != null){
						String strUID  = attrib.getSingleStringValueOrEmptyString();
						setImageData(strUID, input);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DicomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}
	}
}	

class Point3D{
	public Point3D(){
		this.x = 0;
		this.y = 0;
		this.z = 0;		
	}
	
	public Point3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point3D(String str){
		String buffer = str.trim();
		String[] tokens = buffer.split(" ");
		if (tokens.length < 3)
			return;
		
		this.x = Float.valueOf(tokens[0].trim()).floatValue();
		this.y = Float.valueOf(tokens[1].trim()).floatValue();
		this.z = Float.valueOf(tokens[2].trim()).floatValue();
	}
	
	private float x;
	private float y;
	private float z;
	
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	public float getZ() {
		return this.z;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setZ(float z) {
		this.z = z;
	}
}

class ExtensionFilter extends FileFilter {
    private String extensions[];

    private String description;

    public ExtensionFilter(String description, String extension) {
      this(description, new String[] { extension });
    }

    public ExtensionFilter(String description, String extensions[]) {
      this.description = description;
      this.extensions = (String[]) extensions.clone();
    }

    public boolean accept(File file) {
      if (file.isDirectory()) {
        return true;
      }
      int count = extensions.length;
      String path = file.getAbsolutePath();
      for (int i = 0; i < count; i++) {
        String ext = extensions[i];
        if (path.endsWith(ext)
            && (path.charAt(path.length() - ext.length()) == '.')) {
          return true;
        }
      }
      return false;
    }

    public String getDescription() {
      return (description == null ? extensions[0] : description);
    }
}
