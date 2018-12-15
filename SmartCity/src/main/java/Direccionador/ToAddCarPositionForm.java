package Direccionador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structure.Vehicle;
import utilities.VehicleDAO;

/**
 * Servlet implementation class ToAddCarPositionForm
 */
@WebServlet(
		name="ToAddCarPositionForm",
		urlPatterns = {"/addCarPositionForm"}
)
public class ToAddCarPositionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToAddCarPositionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VehicleDAO retriever = new VehicleDAO();
		List<Vehicle> lista = retriever.getList();
		request.setAttribute("cars", lista);
		request.getRequestDispatcher("WEB-INF/jsp/AddCarPosition.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
