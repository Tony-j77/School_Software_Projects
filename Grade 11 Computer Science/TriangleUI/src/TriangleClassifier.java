public class TriangleClassifier {
    
    public String getClassification(int angleA, int angleB, int angleC) {
        if (angleA + angleB + angleC == 180) { //if the three sides add up to 180
             
            if (angleA == angleB && angleB == angleC) { //if all sides equal 

                return "equilateral";
        
            } else if (angleA == angleB || angleB == angleC || angleC == angleA) {  //if 2 of the sides equal
                
                if (angleA == angleB){ //if angleC is the biggest angle
                    
                    if (angleC > angleB + angleA) {
                        return ("obtuse isosceles");
                    } else if (angleC < angleB +angleA) {
                        return ("acute isosceles");
                    } else {
                        return ("right isosceles");
                    }

                } else if (angleA == angleC) {  //if angleB is the biggest angle

                    if (angleB > angleC + angleA) {
                        return ("obtuse isosceles");
                    } else if (angleB < angleC +angleA) {
                        return ("acute isosceles");
                    } else {
                        return ("right isosceles");
                    }

                } else { //if angleA is the biggest angle
                    
                    if (angleA > angleC + angleB) {
                        return ("obtuse isosceles");
                    } else if (angleA < angleC +angleB) {
                        return ("acute isosceles");
                    } else {
                        return ("right isosceles");
                    }
                }

            
            } else {    //if none of the size equal
                
                final int max = Math.max(Math.max(angleA, angleB),angleC); //finds the largest angle of the three
                final int min = Math.min(Math.min(angleA, angleB),angleC);
                final int mid = 180 - max- min;

                if (max > min + mid && max != 0 && min !=0 && mid != 0) {

                    return ("obtuse scalene");
                } else if (max == mid + min) {

                    return ("right scalene");

                } else if(max < min + mid) {

                    return ("acute scalene");

                } else {
                    return ("INVALID");
                }


            }
        
        } else { //if the three angles don't add up to 180
            return ("INVALID");
        }
    }
}
