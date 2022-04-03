import { Food } from '../food';
import { Menu } from '../menu';

export interface Restaurant {
  name: string;
  location: string;
  deliveryZones: string;
  menuList?: Menu[];
  description?: string;
  imgUrl?: string;
}
