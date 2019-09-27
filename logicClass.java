/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univariate_linearregression;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
/**
 *
 * @author admin
 */
public class logicClass {

    private RealMatrix w, estimate;
    public  logicClass(double[][] xArray, double[][] yArray) throws Exception
    {
        applyNormalEquation(MatrixUtils.createRealMatrix(xArray), MatrixUtils.createRealMatrix(yArray));
    }
    private void applyNormalEquation(RealMatrix x, RealMatrix y) throws Exception
    {
        LUDecomposition luDecomposition = new LUDecomposition(x.transpose().multiply(x));

        if(luDecomposition.getDeterminant() == 0.0)
            throw new Exception("Singular matrix with no inverse");
        else {
            w = luDecomposition.getSolver().getInverse().multiply((x.transpose().multiply(y)));
            estimate = x.multiply(w);
        }
    }
    
    public RealMatrix getw(){ return w; }
    public RealMatrix getEstimate() { return estimate; }
    public double estimateRent(String entry)
    {
        return MatrixUtils.createColumnRealMatrix(new double[]{1,Double.valueOf(entry)}).transpose().multiply(w).getData()[0][0];
    }
}
