package com.revature.models;

import java.util.TreeMap;

public class User {

		private int user_ID;
		private String username;
		private String password;
		private String firstName;
		private String lastName;
		private String email;
		private Roles role;
		private int role_ID;
		private TreeMap<Integer, Reimbursement> reimbursements;
				
		public User (UserEntry entry) {
			super();
			this.username = entry.getUsername();
			this.password = entry.getPassword();
			this.firstName = entry.getFirstName();
			this.lastName = entry.getLastName();
			this.email = entry.getEmail();
			this.role_ID = entry.getRole_ID();
		}
		
		public User() {
			super();
		}

		public User(int user_ID, String username, String password, String firstName, String lastName, String email, int role_ID) {
			super();
			this.user_ID = user_ID;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role_ID = role_ID;
		}
		
		public User(String username, String password, String firstName, String lastName, String email, int role_ID) {
			super();
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role_ID = role_ID;
		}

		
		public int getUser_ID() {
			return user_ID;
		}

		
		
		public void setUser_ID(int user_ID) {
					this.user_ID = user_ID;
				}
		
				
		public String getUsername() {
					return username;
				}
		
				
		public void setUsername(String username) {
					this.username = username;
				}
		
				
		public String getPassword() {
					return password;
				}
		
				
		public void setPassword(String password) {
					this.password = password;
				}
		
				
		public String getFirstName() {
					return firstName;
				}
		
				
		public void setFirstName(String firstName) {
					this.firstName = firstName;
				}
		
				
		public String getLastName() {
					return lastName;
				}
		
				
		public void setLastName(String lastName) {
					this.lastName = lastName;
				}
		
				
		public String getEmail() {
					return email;
				}
		
				
		public void setEmail(String email) {
					this.email = email;
				}
		
		
		
		public Roles getRole() {
			return role;
		}

		public void setRole(Roles role) {
			this.role = role;
		}

		public int getRole_ID() {
					return role_ID;
				}
		
				
		public void setRole_ID(int role_ID) {
					this.role_ID = role_ID;
				}

	
				
		public TreeMap<Integer, Reimbursement> getReimbursements() {
			return reimbursements;
		}

		
		public void setReimbursements(TreeMap<Integer, Reimbursement> reimbursements) {
			this.reimbursements = reimbursements;
		}

	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + role_ID;
			result = prime * result + user_ID;
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}

		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (role_ID != other.role_ID)
				return false;
			if (user_ID != other.user_ID)
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		
		@Override
		public String toString() {
			return "User [user_ID=" + user_ID + ", username=" + username + ", password=" + password + ", firstName="
					+ firstName + ", lastName=" + lastName + ", email=" + email + ", role_ID=" + role_ID + "]";
		}
		
		
	
}
