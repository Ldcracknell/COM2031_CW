public class question1 {

    public static void main(String[] args){
        Vector v0 = new Vector(-8.0, 5.0);
        Vector v1 = new Vector(-4.0, -7.0);
        Vector v2 = new Vector(-7.0, -2.0);
        Vector v3 = new Vector(7.0, 6.0);
        Vector v4 = new Vector(6.0, -5.0);
        Vector v5 = new Vector(-5.0, -4.0);
        Vector v6 = new Vector(4.0, 0.0);
        Vector v7 = new Vector(1.0, -3.0);

        Vector[] points = {v0, v1, v2, v3, v4, v5, v6, v7};

        points = sortVectorArrayByX(points);

        //1, print the sorted array by x as comma separated indices
        System.out.println("1 - Sorted by x:");
        for (Vector point : points) {
            System.out.print(point.x() + ", ");
        }
        System.out.println();
        //

        System.out.println(MinDist(points));
    }

    static double MinDist(Vector[] points){
        if(points.length == 1){
            return Double.POSITIVE_INFINITY;
        }
        if(points.length == 2){
            return Math.sqrt(Math.pow(points[0].x() - points[1].x(), 2) + Math.pow(points[0].y() - points[1].y(), 2));
        }

        Vector[] left = new Vector[points.length/2];
        Vector[] right = new Vector[points.length - points.length/2];

        for(int i = 0; i < points.length/2; i++){
            left[i] = points[i];
        }
        for(int i = points.length/2; i < points.length; i++){
            right[i - points.length/2] = points[i];
        }

        //print left
        System.out.println("3 - Left:");
        for (Vector vector : left) {
            System.out.print(vector.x() + ", ");
        }
        System.out.println();
        //print right
        System.out.println("4 - Right:");
        for (Vector vector : right) {
            System.out.print(vector.x() + ", ");
        }
        System.out.println();
        //


        double L = (left[left.length - 1].x() + right[0].x())/2;

        double delta1 = MinDist(left);
        double delta2 = MinDist(right);

        double delta = Math.min(delta1, delta2);

        //pointsLessThanDeltaFromL
        Vector[] pLTDFL = new Vector[points.length];

        int count = 0;

        for (Vector point : points) {
            if (Math.abs(point.x() - L) < delta) {
                pLTDFL[count++] = point;
            }
        }


        //remove nulls
        Vector[] temp = new Vector[count];
        for(int i = 0; i < count; i++){
            temp[i] = pLTDFL[i];
        }
        pLTDFL = temp;



        pLTDFL = sortVectorArrayByY(pLTDFL);

        for(int i = 0; i < pLTDFL.length; i++){
            for(int j = i + 1; j < pLTDFL.length; j++){
                if(pLTDFL[j].y() - pLTDFL[i].y() <= delta){
                    delta = Math.min(delta, Math.sqrt(Math.pow(pLTDFL[i].x() - pLTDFL[j].x(), 2) + Math.pow(pLTDFL[i].y() - pLTDFL[j].y(), 2)));
                }
            }
        }

        return delta;
    }

    public record Vector(double x, double y) {
    }

    static Vector[] sortVectorArrayByX(Vector[] points){
        Vector[] sortedPoints = new Vector[points.length];

        Vector temp = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        int tempIndex = 0;

        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(points[j].x() < temp.x()){
                    temp = points[j];
                    tempIndex = j;
                }
            }
            sortedPoints[i] = temp;
            points[tempIndex] = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            temp = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            tempIndex = 0;
        }

        return sortedPoints;
    }

    static Vector[] sortVectorArrayByY(Vector[] points){
        Vector[] sortedPoints = new Vector[points.length];

        Vector temp = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        int tempIndex = 0;

        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(points[j].y() < temp.y()){
                    temp = points[j];
                    tempIndex = j;
                }
            }
            sortedPoints[i] = temp;
            points[tempIndex] = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            temp = new Vector(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            tempIndex = 0;
        }

        return sortedPoints;
    }

}
