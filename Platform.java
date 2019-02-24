import iiitb.ess201a7.a7base.*;
import java.util.*;
public class Platform {

// all the methods in this class need to be implemented

	public Platform() {

	}
	private ArrayList<Fleet> fleet_list=new ArrayList<Fleet>();
	public void addFleet(Fleet f) {
    fleet_list.add(f);
	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		int car_i=0,id,count=0;
		double time,min_time=0;
		Car temp;

		for(int i=0;i<fleet_list.size();i++)
		{
			temp=fleet_list.get(i).findNearestCar(trip.getStart());
			if(temp!=null){
				time=temp.distSqrd(trip.getStart())/temp.getSpeed();
				if(count==0 && temp.getStatus()==1){min_time=time;car_i=i;}
				else if(count!=0 && min_time>=time && temp.getStatus()==1)
				{
					min_time=time;
					car_i=i;
				}
			}
		}

		try{
			fleet_list.get(car_i).findNearestCar(trip.getStart()).assignTrip(trip);
			return  fleet_list.get(car_i).findNearestCar(trip.getStart());
		}

		catch(Exception e){
			System.out.println("Cars not avaliable");
			return null;
		}

}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> allcar_list=new ArrayList<Car>();
		for(Fleet i:fleet_list){
			allcar_list.addAll(i.getCars());
		}
		return allcar_list;
	}
}
