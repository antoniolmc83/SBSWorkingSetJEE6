package pe.almc.sbs.scheduler;


import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import pe.almc.sbs.service.SBSTasasFacade;


public class SBSTasasJob extends QuartzJobBean{

	private final static Logger logger = LoggerFactory.getLogger(SBSTasasJob.class);
	private SBSTasasFacade sbsTasasFacade;
	

	public SBSTasasJob() {
		System.out.println("---------------");
	}

	public void setSbsTasasFacade(SBSTasasFacade sbsTasasFacade) {
		System.out.println("*****************");
		this.sbsTasasFacade = sbsTasasFacade;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("Iniciando " +  new Date());
		sbsTasasFacade.metodoPrueba();
		logger.info("Finalizando " +  new Date());
	}
	
}
