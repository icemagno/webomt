
package cmabreu.scorpio.action;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import cmabreu.scorpio.misc.PathFinder;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 *  THIS IS NOT USED.
 *  I KEPP THIS FILE TO FUTURE REFERENCES.
 *  WILL DELETE SOON
 * 
 * @author contmagno
 *
 */


@Action(value="generatePdf", results= {  
	    @Result(name="ok", type="httpheader", params={"status", "200"}) }
)   

@ParentPackage("default")
public class GeneratePdfAction extends BasicActionClass {
	private String htmlData;

	public String execute () {
		
		try { 
			HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			response.setCharacterEncoding("UTF-8"); 
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"federation.pdf\"");

			ServletOutputStream outputStream = response.getOutputStream() ; 

			Document document = new Document();
			PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream );
			document.open();	
			document.addAuthor("Carlos Magno Abreu");
			document.addCreator("Scorpio Project");
			document.addSubject("Thanks for your support");
			document.addCreationDate();
			document.addTitle("Federation Details");
		      
			HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			
			
			htmlContext.setImageProvider(new AbstractImageProvider() {
			    public String getImageRootPath() {
			        try {
						return PathFinder.getInstance().getPath() ;
					} catch (UnsupportedEncodingException e) {
						return "";
					}
			    }
			});
			

	        //XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
	        // CSS
	        CSSResolver cssResolver = new StyleAttrCSSResolver();
	        CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(  PathFinder.getInstance().getPath() + "/css/style.css"));
	        cssResolver.addCss(cssFile);			
			
	        PdfWriterPipeline pdf = new PdfWriterPipeline(document, pdfWriter);
	        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	        
	        XMLWorker worker = new XMLWorker(css, true);
	        XMLParser p = new XMLParser(worker);
	        p.parse( new StringReader(htmlData) );
	        
			/*
			CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);
			Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext,
	            new PdfWriterPipeline(document, pdfWriter) ) );
			XMLWorker worker = new XMLWorker(pipeline, true);
			XMLParser p = new XMLParser(worker);
			p.parse( new StringReader(htmlData) );
			*/
			
			
			
			document.close();
	        outputStream.flush(); 
	        outputStream.close(); 
		
		
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "ok";
	}
	
	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}
	
}
